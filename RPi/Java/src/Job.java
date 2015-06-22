import java.util.Date;
class Job{
	Date date;
	String deviceID;
	boolean cmd;
	Job(){
		this.date = new Date();
		this.deviceID = "tets";
		this.cmd = true;
	}
	Job(Date d,String id, boolean cmd){
		this.date = d;
		this.deviceID = id;
		this.cmd = cmd;
	}
	Date getDate(){
		return date;
	}
	String getID(){
		return deviceID;
	}
	boolean getCmd(){
		return cmd;
	}
}	