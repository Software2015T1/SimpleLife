import java.util.*;
import java.io.*;
import java.net.*;
public class RPiSocket{
	

	
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
	private DeviceInfo deviceInfo;
	private DeviceController deviceController;
	public void initial(DeviceInfo deviceInfo,DeviceController deviceController){
		this.deviceInfo = deviceInfo;
		this.deviceController = deviceController;
		
	}
	private BufferedReader in;
	private PrintWriter out;
	public void start(){
		
		try{
		
		String host="192.168.1.52";
		int port=4567;
		Socket s = new Socket(host, port);
		//final Reader from_sever = new InputStreamReader(s.getInputStream());
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream());
		
		while(s!=null){
			String input = in.readLine();
			System.out.println("從Server收到: "+input);
			if(input.equals("end")) 
				break;
			parseCmd(input);
		}
		}
		catch(Exception e){
			System.err.println(e);
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
		//if (outputCmd.equals("end")) 
		//	break;
	}
}