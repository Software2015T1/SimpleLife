import java.util.ArrayList; 
public class IdealTempDeciser{

	IdealTempDecider(){
		
	}
	boolean AddDevice(String in_ID){
		settinglist.add(new IdealTemSetting(in_ID));
		return true;
	}
}

class IdealTemSetting{
	private String arduinoID;
	private int Temp;
	boolean on;
	IdealTemSetting(String in_ID){
		arduinoid = in_ID;
		on = false;
		Temp = 25;
	}
	boolean setTemp(int Temp){
		this.Temp = Temp;
		return true;
	}
	String getArID(){
		return arduinoID;
	}
}