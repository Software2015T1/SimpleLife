package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class Appliance {
    protected String type;
    protected String deviceID;
    protected String mainControllerID;
    protected boolean state;

    public Appliance(String type, String deviceID, String mainControllerID,boolean state){
        this.type = type;
        this.deviceID = deviceID;
        this.mainControllerID = mainControllerID;
        this.state = state;
    }
    public String getType(){
        return type;
    }
    public String getDeviceID(){
        return deviceID;
    }
    public String getMainControllerID(){
        return mainControllerID;
    }
    public boolean getState(){
        return state;
    }
}
