import java.util.Date;
import java.util.Iterator;
public class LightHistoryIterator implements Iterator{
	private Job[] jobarray;
	private int position = 0;
	public LightHistoryIterator(Job[] array){
		jobarray = array;
		position = 0;
	}
	//@Override
	public boolean hasNext(){
		System.out.println("ys");
		if(0>jobarray.length || jobarray.length <= position || jobarray[position]==null)
			return false;
		else 
			return true;
	}
	//@Override 
	public Object next(){
		System.out.println("fuck");
		if(jobarray!=null&& position < jobarray.length){
			Job j = jobarray[position];
			position++;
			return j;
		}
		return null;
	}
	@Override
    public void remove() {  
        throw new java.lang.UnsupportedOperationException();          
    }  
}
