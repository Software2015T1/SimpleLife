package com.example.user.simplelife;

import java.io.Serializable;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class Time implements Serializable{
    private int minute;
    private int hour;
    private String date;

    public Time(int minute, int hour, String date){
        this.minute = minute;
        this.hour = hour;
        this.date = date;
    }
    public int getMinute(){
        return minute;
    }
    public int getHour(){
        return hour;
    }
    public String getDate(){
        return date;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }
    public void getHour(int hour){
        this.hour = hour;
    }
    public void getDate(String date){
        this.date = date;
    }
}
