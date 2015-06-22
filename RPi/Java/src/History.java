import java.util.Iterator; 
import java.util.Date;
import java.util.ArrayList;
public class History{
	LightHistory light;
	ACHistory AC;
	History(LightHistory l,ACHistory ac){
		light = l;
		AC = ac;
	}
	void printHis(){
		System.out.println(1);
		printHis(light.createIterator());
		System.out.println(2);
		printHis(AC.createIterator());
	}
	private void printHis(Iterator i){
		while(i.hasNext()){
			Job j = (Job)i.next();
			System.out.printf("%s\n",j.getID());
		}
	}
	public static void main(String argv[]){
		
		History h = new History(new LightHistory(),new ACHistory());
		h.light.addItem(new Date(),"tet",true);
		h.printHis();
	}
}
