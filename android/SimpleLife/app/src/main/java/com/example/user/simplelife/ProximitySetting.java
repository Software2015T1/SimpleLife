package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class ProximitySetting extends Function{
    private int distance;

    public ProximitySetting(int distance,boolean state,boolean notify){
        this.distance = distance;
        this.state = state;
        this.notify = notify;
    }
    public int getDistance(){
        return distance;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
}
