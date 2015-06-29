package com.example.user.simplelife;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class TimeSetting extends Function implements Cloneable{
    private Time startTime;
    private Time endTime;
    private boolean state;
    private boolean notify;

    public TimeSetting(Time startTime, Time endTime, boolean state, boolean notify){
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
        this.notify = notify;
    }

    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }
    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }

    public Time getStartTime(){
        return startTime;
    }
    public Time getEndTime(){
        return endTime;
    }

    @Override
    protected Object clone()
    {
        return new TimeSetting(this.startTime,this.endTime,this.state,this.notify);
    }

}
