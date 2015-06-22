package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class IdealTemperature extends Function {

    private int temperature;

    public IdealTemperature(int temperature, boolean state, boolean notify){
        this.temperature = temperature;
        this.state = state;
        this.notify = notify;
    }

    public int getTemperature(){
        return  temperature;
    }
    public void getTemperature(int temperature){
        this.temperature = temperature;
    }
}
