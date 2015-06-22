import java.util.*;
public class SmartHome{
	public static RPiSocket rPiSocket = new RPiSocket("MC01");
	public static DeviceController deviceController = new DeviceController();
	public static DeviceInfo deviceInfo = new DeviceInfo(1000);
	public static String MainControllerId;
	//public static DecisionMaker decisionMaker = new DecisionMaker();
	//public static Scheduler scheduler = new Scheduler();
	//public static RF rf = new RF();
	//public static Status status = new Status();
	
	public static void main(String argv[]){
		Calendar cal = Calendar.getInstance();
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    	System.out.printf("mon is %d\n",dayOfWeek);
    	String[] cmdArray = {"/TimeSetting","111"}; 
		rPiSocket.start();
		deviceController.initial(rPiSocket,deviceInfo);
		deviceController.controll(cmdArray);
		//System.out.println("finish building\n");

		rPiSocket.sendCmd("YA!");
		//deviceController.notify("open the light\n");
	}	

}