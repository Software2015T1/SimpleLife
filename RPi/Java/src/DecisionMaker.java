public class DecisionMaker{
	DeviceInfo deviceInfo;
	ArrayList<MotionEvent> eventlist;
	private RF rf;
	
	DecisionMaker(){
	
	}
	boolean Notify(String arduinoid){
		MotionEvent event = new MotionEvent(new Date(),arduinoid);
		for(int i=0;i<eventlist.size();i++){
			MotionEvent tmp = eventlist.get(i);
			if(tmp.arduinoid.equals(event.arduinoid)){
				//check setting parameter
				for(int j=0;j<deviceInfo.deviceCnt;j++){
					
				}
				break;
			}
		}
	}
	boolean NotifyTemerature(String arduinoid){
		
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