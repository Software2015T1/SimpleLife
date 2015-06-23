import java.util.Date;
import java.util.ArrayList;
public class SavingEnergyDecider extends Thread{
	DeviceInfo deviceInfo;
	ArrayList<SavingSetting> settinglist;
	private volatile boolean running = true;
	SavingEnergyDecider(DeviceInfo deviceInfo){
		eventlist =  new ArrayList<MotionEvent>();
		settinglist = new ArrayList<SavingSetting>();
		this.deviceInfo = deviceInfo;
	}
	boolean addDevice(String in_ID){
		settinglist.add(new SavingSetting(in_ID));
		return true;
	}
	void notify(String in_ID,Date date){
		Date cur_event =  new Date();
		for(int i=0;i<eventlist.size();i++){
			SavingEnergy tmp_save = settinglist.get(i);
			if(tmp_save.getArID().equals(in_ID)){
				tmp_save.setEvent(cur_event);
				break;
			}
		}
	}
	@Override
	public void run(){
		SavingEnergy tmp_save;
		int index;
		int id;
		boolean status;
		while(running){
			for(int i=0;i<settinglist.size();i++){
				tmp_save = settinglist.get(i);
				if(!tmp_save.on)continue;
				Date pre_event = tmp_save.getEvent();
				Date cur_event = new Date();
				if(cur_event.getTime()-pre_event.getTime()>tmp_save.GetMin()*60*1000){
					index = deviceInfo.getDeviceIndex(String arduinoID);
					status = deviceInfo.getDeviceId(index);
					id = deviceInfo.getDeviceId(index);
					if(status){
						//off
						
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
	SavingEnergy(String in_ID){
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
	