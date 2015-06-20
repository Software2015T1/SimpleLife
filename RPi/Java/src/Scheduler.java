import java.util.Date;
import java.util.ArrayList; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Scheduler extends Thread{
	
	//http://www.ewdna.com/2011/12/java-timer.html
	private ArrayList<Job> joblist;
	//private RF rf;
	Socket socket;
	public Scheduler(){
		joblist = new ArrayList<>();
	}
	public void initial(){
		
	}
	boolean addJob(String targetID,boolean targetcmd,Date targetdate){
		Job j = new(targetdate,targetID,targetcmd)
		return addJob(j);
	}
	boolean addJob(String targetID,boolean targetcmd,String datesting){
		try{
			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(datesting);
		}catch(ParseException e){
			System.out.println("ParseException");
			e.printStackTrace();
		}
		return addJob(targetID,targetcmd,date);
	}
	boolean addJob(Job j){
		int i;
		if(new Date().after(j.date))
			return false;
		if(joblist.size()==0)
			joblist.add(j);
		for(i=0;i<joblist.size();i++){
			tmp = joblist.get(i);
			if(tmp.date.after(j.date)){
				joblist.add(i,j);
				break;
			}
		}
		if(i==joblist.size())
			joblist.add(j);
		return true;
	}
	boolean deleteJob(Job j){
		joblist.remove(j);
		return true;
	}
	void run(){
		while(1){
			if(joblist.size()==0)continue;
			if(Math.abs(new Date().getTime()- joblist.get(0).getTime())<60*1000){
			//RF send command
			//remove this job
			
				socket.sendCmd()
			}
		}
	}
}
class Job{
	Date date;
	String deviceID;
	boolean cmd;
	Job(Date d,String id, boolean cmd){
		this.date = d;
		this.deviceID = id;
		this.cmd = cmd;
	}
}	