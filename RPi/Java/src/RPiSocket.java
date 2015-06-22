import java.util.*;
import java.io.*;
import java.net.*;
public class RPiSocket{
	
	private DeviceInfo deviceInfo;
	private DeviceController deviceController;
	private Socket s;
	private String mainControllerId;
	private DataInputStream in;
	private DataOutputStream out;
	
	public RPiSocket(String MCid){
		this.mainControllerId=new String(MCid);
	}

	public void initial(DeviceInfo deviceInfo,DeviceController deviceController){
		this.deviceInfo = deviceInfo;
		this.deviceController = deviceController;
		
	}
	


	
	class SeverListener extends Thread{
		@Override
		public void run(){
			try{
				System.out.println("step1\n");
				while(s!=null){
					String input = in.readUTF();
					System.out.println("from sever: "+input);
					if(input.equals("end")) 
						break;
					parseCmd(input);
				}
				System.out.println("step2\n");
			
			} catch (IOException ex)
        	{
            	System.out.println(ex.toString());
        	}


		}



	}



	public void start(){
		
		try{
		
		String host="140.112.53.245";
		int port=1028;
		s = new Socket(host, port);
		in = new DataInputStream(s.getInputStream());
		out = new DataOutputStream(s.getOutputStream());
		String firstConnection="/MCConnect "+mainControllerId;
		out.writeUTF(firstConnection);
	//	System.out.println("finish building\n");
		new SeverListener().start();
	//	System.out.println("finish building2\n");
		}
		
		catch(Exception e){
			System.err.println(e);
		}
	
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
			deviceInfo.addDeviceInfo(cmdArray[2],cmdArray[3],cmdArray[4],cmdArray[5]);
		}
		else if (cmdArray[0].equals("/ControlAppliance")){
		//....
			//deviceController.controll();

		}
		else if (cmdArray[0].equals("/Chart")){
			//history.calculate();
		//....
		}
		else if (cmdArray[0].equals("/TimeSetting")){
			//deviceController.controll();
		//....
		}
		else if (cmdArray[0].equals("/EnergySaver")){
		//....

		}
		else if (cmdArray[0].equals("/WifiSetting")){
			String ssid,password,keytype;


		}
	
	}
	public void sendCmd(String outputCmd){
		//out.println(outputCmd); 
		System.out.println("at the RPiSendCmd"+outputCmd);
		try{
			out.writeUTF(outputCmd);
		}catch (IOException ex)
        {
       		System.out.println(ex.toString());
        }


		//out.flush(); 
		//if (outputCmd.equals("end")) 
		//	break;
	}
}