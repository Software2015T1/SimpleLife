import java.util.Iterator; 
import java.util.*;
import java.util.ArrayList;
public class History{
	LightHistory light;
	ACHistory AC;
	private CalData caldata;
	History(LightHistory l,ACHistory ac){
		light = l;
		AC = ac;
	}
	void addItem(String type,String id,String[] cmd){
		boolean curcmd = true;
		if(cmd[1].equals("off"))
			curcmd = false;
		if(type.equals("Light"))
			light.addItem(new Date(),id,curcmd);
		else if(type.equals("AC"))
			AC.addItem(new Date(),id,curcmd);
	}
	void chart(int cmd){
		Date To = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(To);
		c.set(Calendar.MONTH,c.get(Calendar.MONTH)-1);
		Date From = c.getTime();
		chart(From,To,cmd);
	}
	void chart(Date From,Date To,int cmd){
		if(cmd ==1)
			getData(light.createIterator(),From,To);
		else if (cmd==2)
			getData(AC.createIterator(),From,To);
	}
	
	private double getData(Iterator i,Date From,Date To){
		caldata = new CalData();
		while(i.hasNext()){
			Job j = (Job)i.next();
			Date tmp_date = j.getDate();
			if(tmp_date.after(From)&&tmp_date.before(To))
				caldata.addItem(j);
			else if(tmp_date.after(To))
				break;
		}
		return caldata.getTime();
	}
}
class CalData{
	ArrayList<Job> joblist;
	double time;
	int i;
	CalData(){
		joblist = new ArrayList<Job>();
		time = 0;
	}
	void addItem(Job j){
		for(i=0;i<joblist.size();i++){
			Job tmpJob = joblist.get(i);
			if(tmpJob.getID()==j.getID()&&(tmpJob.getCmd())&&(!j.getCmd())){
				time = time + timeDiff(tmpJob,j);
				joblist.remove(i);
			}
		}
		if(i==joblist.size())
			joblist.add(j);
	}
	double timeDiff(Job joba,Job jobb){
		Date datea = joba.getDate();
		Date dateb = jobb.getDate();
		return (dateb.getTime()-datea.getTime())/(60*60*1000);
	}
	double getTime(){
		return time;
	}
}