import java.util.Date;
import java.util.ArrayList; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Scheduler extends Thread{
	
	//http://www.ewdna.com/2011/12/java-timer.html
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
	boolean addJob(String targetID,boolean targetcmd,Date targetdate){
		Job j = new Job(targetdate,targetID,targetcmd);
		return addJob(j);
	}
	boolean addJob(String targetID,boolean targetcmd,String datestring){
		Date date = new Date();
		System.out.println(datestring);
		try{
			
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(datestring);
		}catch(ParseException e){
			System.out.println("ParseException");
			e.printStackTrace();
		}
		return addJob(targetID,targetcmd,date);
	}
	boolean addJob(Job j){
		int i;
		Job tmp;
		System.out.println("add Job");
		if(new Date().after(j.date))
			return false;
		if(joblist.size()==0)
			joblist.add(j);
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
	boolean deleteJob(Job j){
		joblist.remove(j);
		return true;
	}
	@Override
	public void run(){
		while(running){
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			System.out.println("check ");
			if(joblist.size()==0){
				System.out.println("no Job");
				continue;
			}
			//System.out.printf(" job %d %d %d\n",joblist.get(0).getDate().getYear(),joblist.get(0).getDate().getMonth(),joblist.get(0).getDate().getDate());
			//System.out.println(joblist.get(0).getDate().getTime());
			
			if(Math.abs(new Date().getTime()- joblist.get(0).getDate().getTime())<120*1000){
				
				Job j = joblist.get(0);
				String[] cmd = new String[2];
				cmd[0]="onoff";
				if(j.getCmd())
					cmd[1]="on";
				else 
					cmd[1]="off";
				rf.controll(j.getID(),cmd);
				//rPiSocket.sendCmd(cmd[0]);
				joblist.remove(0);
			}
		}
	}
	void terminate() {
        running = false;
    }
}
