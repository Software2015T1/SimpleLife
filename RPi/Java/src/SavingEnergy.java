import java.util.Date;
import java.util.ArrayList;
public class SavingEnergy extends Thread{
	DeviceInfo deviceInfo;
	ArrayList<SavingSetting> settinglist;
	private volatile boolean running = true;
	private RF rf;
	SavingEnergy(DeviceInfo deviceInfo){
		settinglist = new ArrayList<SavingSetting>();
		this.deviceInfo = deviceInfo;
	}
	boolean addDevice(String in_ID){
		settinglist.add(new SavingSetting(in_ID));
		return true;
	}
	void initial(RF rf){
		this.rf = rf;
	}
	boolean change(String arduinoID,int duration){
		SavingSetting tmp;
		for(int i=0;i<settinglist.size();i++){
			tmp = settinglist.get(i);
			if(arduinoID.equals(tmp.getArID())){
				tmp.setMin(duration);
				break;
			}
		}
		return true;
	}
	void Notify(String in_ID,Date date){
		Date cur_event =  new Date();
		for(int i=0;i<settinglist.size();i++){
			SavingSetting tmp_save = settinglist.get(i);
			if(tmp_save.getArID().equals(in_ID)){
				tmp_save.setEvent(cur_event);
				break;
			}
		}
	}
	@Override
	public void run(){
		SavingSetting tmp_save;
		int index;
		String id;
		boolean status;
		while(running){
			for(int i=0;i<settinglist.size();i++){
				tmp_save = settinglist.get(i);
				if(!tmp_save.on)continue;
				Date pre_event = tmp_save.getEvent();
				Date cur_event = new Date();
				if(cur_event.getTime()-pre_event.getTime()>tmp_save.getMin()*60*1000){
					index = deviceInfo.getDeviceIndex(tmp_save.getArID());
					status = deviceInfo.getDeviceStatus(index);
					id = deviceInfo.getDeviceId(index);
					if(status){
						String[] cmd = new String[2];
						cmd[0]="onoff";
						cmd[1]="off";
						rf.controll(id,cmd);
					}
				}
			}
		}
	}
	void terminate() {
        running = false;
    }
}

class SavingSetting{
	private String arduinoID;
	private int min;
	boolean on;
	Date event;
	SavingSetting(String in_ID){
		arduinoID = in_ID;
		on = false;
		min = 0;
		event = new Date();
	}
	boolean setMin(int min){
		this.min = min;
		return true;
	}
	String getArID(){
		return arduinoID;
	}
	int getMin(){
		return min;
	}
	Date getEvent(){
		return event;
	}
	boolean setEvent(Date date){
		event = date;
		return true;
	}
}
	