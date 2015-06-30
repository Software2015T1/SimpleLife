import java.util.*;
import java.io.*;
public class DeviceInfo implements Serializable{
	private HashMap<String,String> map = new HashMap<String,String>();
	private HashMap<String,String> mapId_type = new HashMap<String,String>();
	public class Device implements Serializable{
		protected String deviceId,arduinoId;
		protected String type;
		protected boolean on;
		
		public Device(){
			deviceId="null";
			arduinoId="null";
			type="null";
			on = false;
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
		map.put(id,ardId);
		mapId_type.put(id,type);
		//System.out.println((String)map.get(id));
		//System.out.println(getArdIDFromMap(id));
		//System.out.println("add complete2");
		
		
		
	}
	int getDeviceIndex(String arduinoId){
		for(int i=0;i<deviceCnt;i++){
			if(arduinoId.equals(deviceList[i].arduinoId))
				return i;
		}
		return 0;
	}
	boolean getDeviceStatus(int index){
		return deviceList[index].on;
	}
	public String getDeviceId(int index){
		return deviceList[index].deviceId;
	}
	public String getArdIDFromMap(String deviceId){
		String returnString = map.get(deviceId);
        if (returnString!=null)
            return returnString;
        else 
            return null;

	}
	public String getTypeFromMap(String deviceId){
		String returnString = mapId_type.get(deviceId);
        if (returnString!=null)
    		return returnString;
        else 
            return null;

	}
	
	
}
