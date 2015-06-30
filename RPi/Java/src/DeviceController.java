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
	//TimeSetting [MC ID] [Device type] [Device ID] [day] [start] [day] [end]
	private int parseWeek(String day){
		String[] week={"Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		for(int i=1;i<=7;i++){
			if (day.equals(week[i-1]))
				return i;
		}
		return 0;
	}
	private Date[] calDate(String[] cmdArray){
		String day1=cmdArray[6],day2=cmdArray[8];
		String time1=cmdArray[7],time2=cmdArray[9];
		String deviceId="light";
		String[] time1Array = time1.split(":");
		String[] time2Array = time2.split(":");
		Calendar cal = Calendar.getInstance();
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    	int duration = (parseWeek(day2)-parseWeek(day1)+7)%7;
		int durationNowtoDay1=(parseWeek(day1)-dayOfWeek+7)%7;
    		
		Date[] cmdDay = new Date[3];
    		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date());
			
		c.add(Calendar.DATE,durationNowtoDay1);
		c.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time1Array[0]));
		c.set(Calendar.MINUTE,Integer.parseInt(time1Array[1]));
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);
		cmdDay[0] = c.getTime();
			
		c.add(Calendar.DATE,duration);
		c.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time2Array[0]));
		c.set(Calendar.MINUTE,Integer.parseInt(time2Array[1]));
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);
		cmdDay[1]=c.getTime();
		
		int day = c.get(Calendar.DATE)-7;
		c.set(Calendar.DATE,day);
		cmdDay[2]=c.getTime();
		return cmdDay;
	}
	public void controll(String[] cmdArray){
		if(cmdArray[0].equals("/ControlAppliance")){
			String[] cmd = new String[2];
			if(cmdArray[4].equals("Light")){
				cmd[0]="onoff";
				cmd[1]=cmdArray[6];
			}else{
				cmd[0]=cmdArray[6];
				cmd[1]=cmdArray[7];
			}
			rf.controll(cmdArray[5],cmd);
		}
		else if(cmdArray[0].equals("/TimeSetting")){
			Date[] cmdDay=calDate(cmdArray);
			scheduler.addJob(cmdArray[5],true,cmdDay[0]);
			scheduler.addJob(cmdArray[5],false,cmdDay[1]);
		}
		else if(cmdArray[0].equals("/TimeSettingRemove")){
			Date[] cmdDay=calDate(cmdArray);
			scheduler.deleteJob(cmdArray[5],true,cmdDay[0]);
			scheduler.deleteJob(cmdArray[5],false,cmdDay[1]);
			scheduler.deleteJob(cmdArray[5],false,cmdDay[2]);
		}
	}
	public void notify(String notification){
		rPiSocket.sendCmd(notification);
	}
}
