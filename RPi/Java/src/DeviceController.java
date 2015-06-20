import java.util.*;
public class DeviceController{

	//private Scheduler scheduler;
	private RPiSocket rPiSocket;
	private DeviceInfo deviceInfo;
	//private RF rf;
	
	public void initial(RPiSocket rPiSocket,DeviceInfo deviceInfo){
		this.deviceInfo = deviceInfo;
		this.rPiSocket = rPiSocket;
		
	} 
	//TimeSetting [MC ID] [Device type] [Device ID] [day] [start] [end]
	public void controll(String[] cmdArray){
	//收到socket的指令決定要開關還是丟scheduler
		if(cmdArray[0].equals("/ControlAppliance")){
			
		
		}
		else if(cmdArray[0].equals("/TimeSetting")){
	

		
		}
		
	}
	private void sendCmdtoRF(){
	
	}
	private void sedCmdtoScheduler(){
	
	}


}