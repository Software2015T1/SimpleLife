package com.example.user.simplelife;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by User on 2015/6/22.
 */
public class ReadFile {

    public static void main(String[]args) throws IOException, ClassNotFoundException{
        FileInputStream fs = new FileInputStream("device.ser");
        ObjectInputStream ois = new ObjectInputStream(fs);
        Device device = (Device)ois.readObject();
        System.out.println(device.name);

    }
}
