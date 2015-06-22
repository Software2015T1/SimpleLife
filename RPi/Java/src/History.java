import java.util.Iterator; 
import java.util.Date;
import java.util.ArrayList;
public class History{
	LightHistory light;
	ACHistory AC;
	private CalData caldata;
	History(LightHistory l,ACHistory ac){
		light = l;
		AC = ac;
	}
	void printHis(Date From,Date To,int cmd){
		if(cmd ==1)
			getData(light.createIterator(),From,To);
		else if (cmd==2)
			getData(AC.createIterator(),From,To);
	}
	private void getData(Iterator i){
		Date cur_date = new Date();
	}
	private double getData(Iterator i,Date From,Date To){
		caldata = new CalData();
		while(i.hasNext()){
			Job j = (Job)i.next();
			Date tmp_date = j.getDate();
			if(tmp_date.after(From)&&tmp_date.before(To)){
				caldata.addItem(j);
			}
			else if(tmp_date.after(To)){
				break;
			}
		}
		return caldata.getTime();
	}
	public static void main(String[] argv){
		
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