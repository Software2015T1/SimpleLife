public class DecisionMaker{
	private DeviceInfo deviceInfo;
	private SavingEnergy save;
	private SafetyIssue safe;
	private IdealTemp ideal;
	private RF rf;
	
	DecisionMaker(){
		save =  new SavingEnergyDecider(rf,deviceInfo);
		safe = new SafetyIssueDecider(rf,deviceInfo);
		ideal = new IdealTemp(rf,deviceInfo);
	}
	void initial(RF rf,DeviceInfo deviceInfo){
		this.rf = rf;
		this.deviceInfo = deviceInfo;
	}
	boolean AddDevice(String in_id){
		return save.AddDevice(in_id)&&safe.AddDevice(in_id)&&ideal.AddDevice(in_id);
	}
	void Notify(String arduinoid){
		save.Notify(arduinoid,new Date());
		Safe.Moving(arduinoid);
	}
}

