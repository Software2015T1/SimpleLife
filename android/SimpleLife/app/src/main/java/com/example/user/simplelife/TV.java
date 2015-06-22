package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class TV extends Appliance{

    public TV( String deviceID, String mainControllerID,String name,boolean state){
        super("TV",deviceID,mainControllerID,name,state);
        image = R.drawable.circle_tv;
    }
}
