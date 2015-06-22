import java.util.ArrayList; 
public class IdealTempDeciser{

	IdealTempDecider(){
		
	}
	boolean AddDevice(String in_id){
		settinglist.add(new IdealTemSetting(in_id));
		return true;
	}
}

class IdealTemSetting{
	private String arduinoid;
	private int Temp;
	boolean on;
	IdealTemSetting(String in_id){
		arduinoid = in_id;
		on = false;
		Temp = 25;
	}
	boolean SetTemp(int Temp){
		this.Temp = Temp;
		return true;
	}
	String GetArID(){
		return arduinoid;
	}
}