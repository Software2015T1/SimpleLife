import java.util.Date;
import java.util.ArrayList; 
import java.util.Iterator;
public class ACHistory implements IHistory{
	private ArrayList<Job> joblist;
	public ACHistory(){
		joblist = new ArrayList<Job>();
		addItem(new Date(),"test",true);
	}
	void addItem(Date d,String id,boolean cmd){
		joblist.add(new Job(d,id,cmd));
	}
	public Iterator createIterator(){
		return joblist.iterator();
	}
}