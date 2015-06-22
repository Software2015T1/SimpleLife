public class DecisionMaker{
	DeviceInfo deviceInfo;
	ArrayList<MotionEvent> eventlist;
	private SavingEnergy save;
	private SafetyIssue safe;
	private IdealTemp ideal;
	private RF rf;
	
	DecisionMaker(){
		/*eventlist = new ArrayList<MotionEvent>();
		safelist = new ArrayList<SafetySetting>();
		savelist = new ArrayList<SavingEnergy>();
		idealist = new ArrayList<IdealTem>();*/
		save =  new SavingEnergyDecider(rf);
		safe = new SafetyIssueDecider(rf);
		ideal = new IdealTemp(rf)
	}
	boolean AddDevice(String in_id){
		return save.AddDevice(in_id)&&safe.AddDevice(in_id)&&ideal.AddDevice(in_id);
	}
	void Notify(String arduinoid){
		save.Notify(arduinoid,new Date());
		Safe.Moving(arduinoid);
	}
}

