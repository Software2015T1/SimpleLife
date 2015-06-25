import java.util.ArrayList; 
public class IdealTemp{
	ArrayList<IdealTemSetting> ideallist;
	IdealTemp(){
		ideallist = new ArrayList<IdealTemSetting>();
	}
	boolean addDevice(String in_ID){
		ideallist.add(new IdealTemSetting(in_ID));
		return true;
	}
	boolean change(String in_id,int new_T){
	
		return true;
	}
}

class IdealTemSetting{
	private String arduinoID;
	private int Temp;
	boolean on;
	IdealTemSetting(String in_ID){
		arduinoID = in_ID;
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