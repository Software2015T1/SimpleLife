import java.util.*;
public class DeviceInfo{
	public class Device{
		protected String deviceId,arduinoId;
		protected String type;	
		public Device(){
			deviceId="null";
			arduinoId="null";
			type="null";
			//cmd="null";
		}
		public String getID(){
			return deviceId;
		}
		public String getArdID(){
			return arduinoId;
		}
		public String getType(){
			return type;
		}
		public void setDevice(String deviceId,String arduinoId,String type){
			this.deviceId=new String(deviceId);
			this.arduinoId=new String(arduinoId);
			this.type=new String(type);
		
		}
	}
	public class Television extends Device{
		private String brand;
		public Television(String deviceId,String arduinoId,String type,String brand){
			this.deviceId=new String(deviceId);
			this.arduinoId=new String(arduinoId);
			this.type=new String(type);
			this.brand=new String(brand);
		}
		public void setBrand(String B){
			this.brand=new String(B);
		}
	}
	public class AirConditioner extends Device{
		private String brand;
		public AirConditioner(String deviceId,String arduinoId,String type,String brand){
			this.deviceId=new String(deviceId);
			this.arduinoId=new String(arduinoId);
			this.type=new String(type);
			this.brand=new String(brand);
		}
		public void setBrand(String B){
			this.brand=new String(B);
		}
	}
	public class Light extends Device{
		private String motionSensor;
		public Light(String deviceId,String arduinoId,String type,String motionSensor){
			this.deviceId=new String(deviceId);
			this.arduinoId=new String(arduinoId);
			this.type=new String(type);
			this.motionSensor=new String(motionSensor);
		}
		public void setMotionSensor(String MS){
			this.motionSensor=new String(MS);
		}
	}
	
	private Device deviceList[];
	private int deviceCnt;
	public DeviceInfo(int maxAmount){
		deviceList=new Device[maxAmount];
		deviceCnt=0;
	}
	//AddAppliance[MC ID][Device type][Arduino ID][Device ID][cmd]
	public void addDeviceInfo(String type,String ardId,String id,String cmd){
	//新增家電
		Device newDevice;
		if(type.equals("light")){
			newDevice = new Light(id,ardId,type,cmd);
			deviceList[deviceCnt]=newDevice;
			deviceCnt++;
		}
		if(type.equals("AC")){
			newDevice = new AirConditioner(id,ardId,type,cmd);
			deviceList[deviceCnt]=newDevice;
			deviceCnt++;
		}
		if(type.equals("TV")){
			newDevice = new Television(id,ardId,type,cmd);
			deviceList[deviceCnt]=newDevice;
			deviceCnt++;
		}
		
		deviceList[deviceCnt].setDevice(id,ardId,type);
		
	}
	//public int getDeviceInfo(){
	//
	
	//}

	
}