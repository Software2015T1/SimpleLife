public class DecisionMaker{
	DeviceInfo device
	ArrayList<MotionEvent> eventlist;
	DecisionMaker(){
	
	}
	boolean Notify(String arduinoid){
		MotionEvent event = new MotionEvent(new Date(),arduinoid);
		
	}
	
}

class MotionEvent{
	Date date;
	String arduinoid;
	MotionEvent(Date date,String id){
		this.date = date;
		this.arduinoid = id;
	}
}