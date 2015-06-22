package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class Other extends Appliance {

    private TimeSetting timeSetting;
    public Other(String deviceID, String mainControllerID,String name,boolean state){
        super("Other",deviceID,mainControllerID,name,state);
        image = R.drawable.circle_other_white;
    }

    public TimeSetting getTimeSetting(){
        return timeSetting;
    }
    public void setTimeSetting(TimeSetting timeSetting){
        this.timeSetting = timeSetting;
    }
}
