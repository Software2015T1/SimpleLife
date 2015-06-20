public class DeviceInfo{
	private class Device{
		private String deviceId,arduinoId;
		private String type,cmd;	
		public Device(){
			deviceId="null";
			arduinoId="null";
			type="null";
			cmd="null";
		}
		public String getID(){
			return deviceId;
		}
		public String getArdID(){
			return arduinoId;
		}
		public String getCmd(){
			return cmd;
		}
		public String getType(){
			return type;
		}
		public setDevice(String deviceId,String arduinoId,String type,String cmd){
			this.deviceId=deviceId;
			this.arduinoId=arduinoId;
			this.type=new String(type);
			this.cmd= new String(cmd);
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
		deviceList[deviceCnt].setDevice(id,ardId,type,cmd);
		
	}
	public int getDeviceInfo(){
	
	}

	
}