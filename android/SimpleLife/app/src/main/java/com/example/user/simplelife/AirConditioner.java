package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class AirConditioner extends Appliance{

    private static String[] strength = {"auto","weak","normal","strong"};
    private static String[] direction = {"auto","0","45","90"};
    private String motionID;
    private TimeSetting timeSetting;
    private ProximitySetting proximitySetting;
    private EnergySaver energySaver;
    private IdealTemperature idealTemperature;
    private int temperature;
    private int strength_id;
    private int direction_id;

    public AirConditioner( String deviceID, String mainControllerID,String mainControllerName,String name,boolean state){
        super("AC",deviceID,mainControllerID,mainControllerName,name,state);
        image = R.drawable.circle_air;
        temperature  = 25;
        strength_id = 0;
        direction_id = 0;
    }
    public AirConditioner(){
        super();
        type = "AC";
        image = R.drawable.circle_air;
    }

    public String getMotionID(){
        return motionID;
    }
    public TimeSetting getTimeSetting(){
        return timeSetting;
    }
    public ProximitySetting getProximitySetting(){
        return proximitySetting;
    }
    public EnergySaver getEnergySaver(){
        return  energySaver;
    }
    public IdealTemperature getIdealTemperature(){
        return  idealTemperature;
    }

    public void setTimeSetting(TimeSetting timeSetting){
        this.timeSetting = timeSetting;
    }
    public void setProximitySetting(ProximitySetting proximitySetting){
        this.proximitySetting = proximitySetting;
    }
    public void setEnergySaver(EnergySaver energySaver){
        this.energySaver = energySaver;
    }
    public void setIdealTemperature(IdealTemperature idealTemperature){
        this.idealTemperature = idealTemperature;
    }
    public void setMotionID(String motionID){
        this.motionID = motionID;
    }
    public int getTemperature(){
        return temperature;
    }
    public void setTemperature(int temperature){
        this.temperature =  temperature;
    }

    public void changeStrength(){
        strength_id+=1;
        strength_id = strength_id%4;
    }
    public void changeDirection(){
        direction_id+=1;
        direction_id = direction_id%4;
    }
    public String getStrength(){
        return AirConditioner.strength[strength_id];
    }
    public String getDirection(){
        return AirConditioner.direction[direction_id];
    }

}
