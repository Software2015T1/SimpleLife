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
	//����socket�����O�M�w�n�}���٬O��scheduler
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