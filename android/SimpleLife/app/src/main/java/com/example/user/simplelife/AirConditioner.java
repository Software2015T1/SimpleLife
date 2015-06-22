package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class AirConditioner extends Appliance{
    private String motionID;
    private TimeSetting timeSetting;
    private ProximitySetting proximitySetting;
    private EnergySaver energySaver;
    private IdealTemperature idealTemperature;

    public AirConditioner(String type, String deviceID, String mainControllerID,boolean state){
        super(type,deviceID,mainControllerID,state);
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


}
