import java.util.Date;
import java.util.ArrayList; 
public class Scheduler{
	
	//http://www.ewdna.com/2011/12/java-timer.html
	private ArrayList<Job> joblist;
	//private RF rf;
	
	public Scheduler(){
		joblist = new ArrayList<>();
	}
	public void initial(){
		
	}
	boolean addJob(int targetID,boolean targetcmd,Date targetdate){
		Job j = new(targetdate,targetID,targetcmd)
		return addJob(j);
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
	boolean checkList(){
		if (Math.abs(new Date().getTime()- joblist.get(0).getTime())<60*1000){
			//RF send command
			//remove this job
		}
		return true;
	}
}
class Job{
	Date date;
	int deviceID;
	boolean cmd;
	Job(Date d,int id, boolean cmd){
		this.date = d;
		this.deviceID = id;
		this.cmd = cmd;
	}
}	