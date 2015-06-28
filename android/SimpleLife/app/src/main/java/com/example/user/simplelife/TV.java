package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class TV extends Appliance{

    private String brand;
    public TV( String arduinoID, String mainControllerID,String mainControllerName,String name,boolean state,String deviceID){
        super("TV",arduinoID,mainControllerID,mainControllerName,name,state,deviceID);
        image = R.drawable.circle_tv;
    }
    public TV(){
        super();
        type = "TV";
        image = R.drawable.circle_tv;
    }
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
}
