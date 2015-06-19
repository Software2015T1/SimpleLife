public class Socket{
	

	
	/*public static void start throws IOException {
		String host="8.8.8.8";
		int port="4567";
		System.out.println("試圖連線到Server...");
		Socket server = new Socket(host, port);
		System.out.println("成功連線到Server");
		ClientReceiver receiver = new ClientReceiver(server);
		receiver.start();
		PrintWriter out = new PrintWriter(server.getOutputStream());
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String str = keyboard.readLine();
			out.println(str);
			out.flush();
			if (str.equals("end")) 
				break;
		}
		receiver.stopSafely();
		server.close();
	}*/
	public start(){
		String host="8.8.8.8";
		int port="4567";
		Socket s = new Socket(host, port);
		//final Reader from_sever = new InputStreamReader(s.getInputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter out = new PrintWriter(s.getOutputStream());
		
		while(s!=null){
			String input = in.readLine();
			System.out.println("從Server收到: "+input);
			if(input.equals("end")) 
				break;
			parseCmd(input);
		}
		
	//	BufferedReader from_user= new BufferedReader(new InputStreamReader(System.in));
	
	}
	public void parseCmd(String inputCmd){
	
		String[] cmdArray = inputCmd.split(" ");
		/*
		AddAppliance 
		ControlAppliance 
		Chart
		TimeSetting
		ProximitySetting
		EnergySaver
		*/
		
		
		if (cmdArray[0].equals("/AddAppliance")){
		//....
		}
		else if (cmdArray[0].equals("/ControlAppliance")){
		//....
		}
		else if (cmdArray[0].equals("/Chart")){
		//....
		}
		else if (cmdArray[0].equals("/TimeSetting")){
		//....
		}
		else if (cmdArray[0].equals("/ProximitySetting")){
		//....
		}
		else if (cmdArray[0].equals("/EnergySaver")){
		//....
		}
	
	}
	public void sendCmd(String outputCmd){
		out.println(outputCmd); 
		out.flush(); 
		if (outputCmd.equals("end")) 
			break;
	}
}