import java.util.Date;
public class DecisionMaker{
	private DeviceInfo deviceInfo;
	private SavingEnergy save;
	private IdealTemp ideal;
	private RF rf;
	
	DecisionMaker(){
		save =  new SavingEnergy(deviceInfo);
		ideal = new IdealTemp();
	}
	void initial(RF rf,DeviceInfo deviceInfo){
		this.rf = rf;
		this.deviceInfo = deviceInfo;
		save.initial(rf);
	}
	boolean addDevice(String in_id){
		return save.addDevice(in_id)&&ideal.addDevice(in_id);
	}
	boolean change(String in_id,int type,int variable){
		if(type==1)
			save.change(in_id,variable);
		else if(type==1)
			ideal.change(in_id,variable);
		return true;
	}
	void Notify(String arduinoid){
		save.Notify(arduinoid,new Date());
	}
}

