import java.util.Date;
import java.util.Iterator;
public class LightHistory implements IHistory{
	public static final int MAX_ITEMS = 6000;  
	private Job[] jobarray;
	private int numberOfJob;
	
	public LightHistory(){
		jobarray = new Job[MAX_ITEMS];
		numberOfJob = 0;
	}
	boolean addItem(Date d,String id,boolean cmd){
		if(numberOfJob>=MAX_ITEMS)
			return false;
		jobarray[numberOfJob] = new Job(d,id,cmd);
		numberOfJob ++;
		return true;
	}
	public Iterator createIterator(){
		return new LightHistoryIterator(jobarray);
	}
}
