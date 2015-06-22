package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class Other extends Appliance {
    private TimeSetting timeSetting;

    public Other(String type, String deviceID, String mainControllerID,boolean state){
        super(type,deviceID,mainControllerID,state);
    }

    public TimeSetting getTimeSetting(){
        return timeSetting;
    }
    public void setTimeSetting(TimeSetting timeSetting){
        this.timeSetting = timeSetting;
    }
}
