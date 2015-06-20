import java.util.*;
public class SmartHome{
	public static RPiSocket rPiSocket = new RPiSocket();
	public static DeviceController deviceController = new DeviceController();
	public static DeviceInfo deviceInfo = new DeviceInfo(1000);
	//public static DecisionMaker decisionMaker = new DecisionMaker();
	//public static Scheduler scheduler = new Scheduler();
	//public static RF rf = new RF();
	//public static Status status = new Status();
	
	public static void main(String argv[]){
		rPiSocket.start();
	}	

}