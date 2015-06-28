import java.util.Date;
import java.util.ArrayList; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Scheduler extends Thread{
	
	//http://www.ewdna.com/2011/12/java-timer.html
	public static Object lock = new Object();
	private ArrayList<Job> joblist;
	private volatile boolean running = true;
	private RF rf;
	RPiSocket rPiSocket;
	public Scheduler(){
		joblist = new ArrayList<Job>();
	}
	void initial(RF rf ,RPiSocket rPiSocket){
		this.rf = rf;
		this.rPiSocket=rPiSocket;
	}
	boolean addJob(String targetID,boolean targetcmd,Date date){
		Job j = new Job(date,targetID,targetcmd);
		return addJob(j);
	}
	boolean addJob(Job j){
		int i;
		Job tmp;
		
		if(new Date().after(j.date))
			return false;
		if(joblist.size()==0){
			joblist.add(j);
			return true;
		}
		for(i=0;i<joblist.size();i++){
			tmp = joblist.get(i);
			if(tmp.getDate().after(j.date)){
				joblist.add(i,j);
				break;
			}
		}
		if(i==joblist.size())
			joblist.add(j);
		return true;
	}
	boolean deleteJob(String targetID,boolean targetcmd,Date date){
		Job j = new Job(date,targetID,targetcmd);
		return deleteJob(j);

	}
	boolean deleteJob(Job j){
		joblist.remove(j);
		return true;
	}
	@Override
	public void run(){
		
		while(running){
         //System.out.println("scheduler check");
			try{
				Thread.sleep(500);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			if(joblist.size()==0)
				continue;
			if(Math.abs(new Date().getTime()- joblist.get(0).getDate().getTime())<30*1000){
				Job j = joblist.get(0);
				String[] cmd = new String[2];
				cmd[0]="onoff";
				if(j.getCmd())
					cmd[1]="on";
				else 
					cmd[1]="off";
				rf.controll(j.getID(),cmd);
				Calendar c = Calendar.getInstance();
				c.setTime(j.getDate());
				c.set(Calendar.DATE,c.get(Calendar.DATE)+7);
				Date date = c.getTime();
				addJob(j.getID(),j.getCmd(),date);
					//rPiSocket.sedndCmd(cmd[0]);
				joblist.remove(j);
			}
		}
	}
	void terminate() {
        running = false;
    }
}
