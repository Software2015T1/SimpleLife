import java.util.*;
import java.io.*;
import java.net.*;
public class RPiSocket{
	private DeviceInfo deviceInfo;
	private DeviceController deviceController;
	private History history;
	private DecisionMaker decisionMaker;
	private Socket s;
	private String mainControllerId;
	private DataInputStream in;
	private DataOutputStream out;
	
	public RPiSocket(String MCid){
		this.mainControllerId=new String(MCid);
	}
	public void initial(DeviceInfo deviceInfo,DeviceController deviceController,History history,DecisionMaker decisionMaker){
		this.deviceInfo = deviceInfo;
		this.deviceController = deviceController;
		this.history = history;
		this.decisionMaker = decisionMaker;
	}	
	class SeverListener extends Thread{
		@Override
		public void run(){
			try{
				while(s!=null){
                    System.out.println("wait from server");
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
			//System.out.println("finish building\n");
			new SeverListener().start();
			//System.out.println("finish building2\n");
		}catch(Exception e){
			System.err.println(e);
		}
	
	}
	public void parseCmd(String inputCmd){
	
		String[] cmdArray = inputCmd.split(" ");		
		
		if (cmdArray[0].equals("/AddAppliance")){
			System.out.printf("add appliance\n");
			deviceInfo.addDeviceInfo(cmdArray[4],cmdArray[5],cmdArray[6],cmdArray[7]);
            try{
               ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../DeviceInfo.object"));
                oos.writeObject(deviceInfo);
                oos.flush();
                oos.close();
            }catch(IOException e){
                e.printStackTrace();
            }
			decisionMaker.addDevice(cmdArray[7]);
		}
		else if (cmdArray[0].equals("/ControlAppliance")){
			deviceController.controll(cmdArray);
		}
		else if (cmdArray[0].equals("/Chart")){
			history.chart(1);
		}
		else if (cmdArray[0].equals("/TimeSetting")){
			deviceController.controll(cmdArray);
		}
		else if (cmdArray[0].equals("/EnergySaver")){
			decisionMaker.change(cmdArray[5],1,Integer.valueOf(cmdArray[6]));
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
