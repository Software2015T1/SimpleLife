package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class Light extends Appliance{
    private String motionID;
    private TimeSetting timeSetting;
    private ProximitySetting proximitySetting;
    private EnergySaver energySaver;

    public Light(String arduinoID, String mainControllerID,String mainControllerName,String name,boolean state,String deviceID){
        super("Light",arduinoID,mainControllerID,mainControllerName,name,state,deviceID);
        image = R.drawable.circle_light;
        icon = R.drawable.light_icon;
    }
    public Light(){
        super();
        type = "Light";
        image = R.drawable.circle_light;
        icon = R.drawable.light_icon;
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
    public void setTimeSetting(TimeSetting timeSetting){
        this.timeSetting = timeSetting;
    }
    public void setProximitySetting(ProximitySetting proximitySetting){
        this.proximitySetting = proximitySetting;
    }
    public void setEnergySaver(EnergySaver energySaver){
        this.energySaver = energySaver;
    }
    public void setMotionID(String motionID){
        this.motionID = motionID;
    }
}
