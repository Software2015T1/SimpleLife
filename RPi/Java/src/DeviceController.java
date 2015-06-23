import java.util.*;
public class DeviceController{

	private RPiSocket rPiSocket;
	private DeviceInfo deviceInfo;
	private History history;
	private DecisionMaker decisionMaker;
	private Scheduler scheduler;
	private RF rf;
	
	public void initial(RPiSocket rPiSocket,DeviceInfo deviceInfo,DecisionMaker decisionMaker, Scheduler scheduler,RF rf){
		this.deviceInfo = deviceInfo;
		this.rPiSocket = rPiSocket;
		this.decisionMaker = decisionMaker;
		this.scheduler = scheduler;
		this.rf = rf;
	} 
	//TimeSetting [MC ID] [Device type] [Device ID] [day] [start] [end]
	private int parseWeek(String day){
		String[] week={"Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		for(int i=1;i<=7;i++){
			if (day.equals(week[i-1]))
				return i;
		}
		return 0;
	}
	public void controll(String[] cmdArray){
		if(cmdArray[0].equals("/ControlAppliance")){
			String[] cmd = new String[2];
			cmd[0]=cmdArray[6];
			cmd[1]=cmdArray[7];
			rf.controll(cmdArray[5],cmd);
		}
		else if(cmdArray[0].equals("/TimeSetting")){
			String day1=cmdArray[6],day2=cmdArray[8];
			String time1=cmdArray[7],time2=cmdArray[9];
			String deviceId="light";
			String[] time1Array = time1.split(":");
			String[] time2Array = time2.split(":");
			Calendar cal = Calendar.getInstance();
    		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    		int duration = (parseWeek(day2)-parseWeek(day1)+7)%7;
    		int durationNowtoDay1=(parseWeek(day1)-dayOfWeek+7)%7;
    		Date startDay = new Date();
    		Date endDay = new Date();
    		Calendar c = Calendar.getInstance(); 
			c.setTime(startDay); 
			//System.out.printf("%d----%d --- %d\n",dayOfWeek,parseWeek(day1),parseWeek(day2));
			c.add(Calendar.DATE,durationNowtoDay1);
			c.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time1Array[0]));
			c.set(Calendar.MINUTE,Integer.parseInt(time1Array[1]));
			c.set(Calendar.SECOND,0);
			c.set(Calendar.MILLISECOND,0);
			startDay = c.getTime();
			
			c.add(Calendar.DATE,duration);
			c.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time2Array[0]));
			c.set(Calendar.MINUTE,Integer.parseInt(time2Array[1]));
			c.set(Calendar.SECOND,0);
			c.set(Calendar.MILLISECOND,0);
			endDay=c.getTime();

			scheduler.addJob(cmdArray[5],true,startDay);
			scheduler.addJob(cmdArray[5],false,endDay);

		
		}
	}
	public void notify(String notification){
		rPiSocket.sendCmd(notification);
	}
}