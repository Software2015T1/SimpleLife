package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public abstract class Device {
    protected String name;
    protected String type;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType(){
        return type;
    }
}
