package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class Appliance extends Device{
    protected int image;
    protected int icon;
    protected String deviceID;
    protected String mainControllerID;
    protected boolean state;
    protected String mainControllerName;

    public Appliance(String type, String deviceID, String mainControllerID,String mainControllerName,String name,boolean state){
        this.type = type;
        this.deviceID = deviceID;
        this.mainControllerID = mainControllerID;
        this.mainControllerName = mainControllerName;
        this.state = state;
        this.name = name;
    }
    public Appliance(){
        state = false;
    }
    public void setDeviceID(String deviceID){
        this.deviceID = deviceID;
    }
    public String getDeviceID(){
        return deviceID;
    }
    public void setMainControllerID(String mainControllerID){
        this.mainControllerID =  mainControllerID;
    }
    public String getMainControllerID(){
        return mainControllerID;
    }
    public void setMainControllerName(String mainControllerName){
        this.mainControllerName =  mainControllerName;
    }
    public String getMainControllerName(){
        return mainControllerName;
    }
    public boolean getState(){
        return state;
    }
    public void setState(boolean state){
        this.state = state;
    }
    public int getImage(){
        return image;
    }
    public int getIcon(){
        return icon;
    }

}
