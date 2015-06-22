package com.example.user.simplelife;

import java.io.Serializable;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class Device implements Serializable {
    protected String name;

    public Device() {

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
