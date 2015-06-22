import java.util.Date;
import java.util.ArrayList;
public class SavingEnergyDecider extends Thread{
	
	ArrayList<MotionEvent> eventlist;
	ArrayList<SavingSetting> settinglist;
	private volatile boolean running = true;
	SavingEnergyDecider(){
		eventlist =  new ArrayList<MotionEvent>();
		settinglist = new ArrayList<SavingSetting>();
	}
	boolean AddDevice(String in_id){
		settinglist.add(new SavingSetting(in_id));
		eventlist.add(new MotionEvent(new Date(),in_id));
		return true;
	}
	void Notify(String in_id,Date date){
		
	}
	@Override
	public void run(){
		while(running){
			for(int i=0;i<)
		}
	}
	void terminate() {
        running = false;
    }
}

class SavingSetting{
	private String arduinoid;
	private int min;
	boolean on;
	SavingEnergy(String in_id){
		arduinoid = in_id;
		on = false;
		min = 0;
	}
	boolean SetMin(int min){
		this.min = min;
	}
	String GetArID(){
		return arduinoid;
	}
	String GetMin(){
		return min;
	}
}

class MotionEvent{
	public String arduinoid;
	Date date;
	MotionEvent(Date date,String id){
		this.date = date;
		this.arduinoid = id;
	}
}
