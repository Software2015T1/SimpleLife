import java.util.*;
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
		Calendar cal = Calendar.getInstance();
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    	//String[] cmdArray = {"/TimeSetting","111"}; 
		rPiSocket.initial(deviceInfo,deviceController,history,decisionMaker);
		rPiSocket.start();
		deviceController.initial(rPiSocket,deviceInfo,decisionMaker,scheduler,rf);
		scheduler.initial(rf);
		decisionMaker.initial(rf,deviceInfo);
		//deviceController.controll(cmdArray);
		

		//rPiSocket.sendCmd("YA!");
		//deviceController.notify("open the light\n");
	}	

}