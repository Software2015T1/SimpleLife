

import java.io.IOException;
import java.util.*;
import java.io.*;
public class SmartHome{
	public static RPiSocket rPiSocket = new RPiSocket("MC01");
	public static DeviceController deviceController = new DeviceController();
	public static DeviceInfo deviceInfo = new DeviceInfo(1000);
	public static String MainControllerId;
	public static Scheduler scheduler = new Scheduler();
	public static History history = new History(new LightHistory(),new ACHistory());
	public static DecisionMaker decisionMaker = new DecisionMaker();
	public static RF rf = new RF();
	
	public static void main(String argv[]){
        try{
            ObjectInputStream ois = new ObjectInputStream( new FileInputStream("../DeviceInfo.object"));
            deviceInfo = (DeviceInfo)ois.readObject();
            ois.close();
        }catch(Exception e){
            e.printStackTrace();
        }
		Calendar cal = Calendar.getInstance();
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    	//String[] cmdArray = {"/TimeSetting","111"}; 
		rPiSocket.initial(deviceInfo,deviceController,history,decisionMaker);
		rPiSocket.start();
		deviceController.initial(rPiSocket,deviceInfo,decisionMaker,scheduler,rf);
		scheduler.initial(rf,rPiSocket);
		decisionMaker.initial(rf,deviceInfo);
		//deviceController.controll(cmdArray);
		scheduler.start();
		rf.start();
        rf.initial(deviceInfo);
		//rPiSocket.sendCmd("YA!");
		//deviceController.notify("open the light\n");
		//System.out.printf("add appliance\n");
		/*rPiSocket.parseCmd("/AddAppliance chander password MC001 light AD001 lgt001 MS001");
		System.out.println("add complete1");
		rPiSocket.parseCmd("/AddAppliance chander password MC001 TV AD002 TV001 Sony");
		rPiSocket.parseCmd("/AddAppliance chander password MC001 AC AD001 AC001 LC");
		System.out.println(deviceInfo.getArdIDFromMap("AC001"));
		
		rPiSocket.parseCmd("/ControlAppliance chander password MC001 light lgt001 onoff on");
		rPiSocket.parseCmd("/TimeSetting chander password MC001 light lgt001 Sun 18:37 Sun 18:38");*/
	}	

}
