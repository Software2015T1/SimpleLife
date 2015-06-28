package com.example.user.simplelife;

import java.io.Serializable;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class Appliance extends Device implements Serializable{
    protected int image;
    protected int icon;
    protected String arduinoID;
    protected String deviceID;
    protected String mainControllerID;
    protected boolean state;
    protected String mainControllerName;

    public Appliance(String type, String arduinoID, String mainControllerID,String mainControllerName,String name,boolean state,String deviceID){
        this.type = type;
        this.arduinoID = arduinoID;
        this.mainControllerID = mainControllerID;
        this.mainControllerName = mainControllerName;
        this.state = state;
        this.name = name;
        this.deviceID = deviceID;
    }
    public Appliance(){
        state = false;
    }
    public void setArduinoID(String arduinoID){
        this.arduinoID = arduinoID;
    }
    public String getArduinoID() {
        return arduinoID;
    }
    public String getDeviceID(){return this.deviceID;}
    public void setDeviceID(String deviceID){this.deviceID =deviceID;}
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
