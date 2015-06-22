import java.util.ArrayList; 
public class SafetyIssueDecider{
	ArrayList<SafetySetting> settinglist;
	RF rf;
	SafetyIssueDecider(RF rf){
		this.rf = rf;
	}
	Moving(String arduino){
		boolean on;
		for(int i=0;i<settinglist.size();i++){
			SafetySetting = settinglist.get(i);
			
		}
		
	}
	boolean AddDevice(String in_id){
		settinglist.add(new SafetySetting(in_id));
		return true;
	}
}

class SafetySetting{
	private  String arduinoid;
	boolean on;
	SafetySetting(String in_id){
		arduinoid = in_id;
		on = false;
	}
	String GetArID(){
		return arduinoid;
	}
}