package com.example.user.simplelife;

import java.io.Serializable;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public abstract class Function implements Serializable{
    protected boolean state;
    protected boolean notify;

    public boolean getState(){
        return state;
    }
    public boolean getNotify(){
        return notify;
    }

    public void setState(boolean state){
        this.state = state;
    }
    public void setNotify(){
        this.notify = notify;
    }
}
