package com.example.user.simplelife;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by Smith on 2015/6/23.
 */
public class ObjectReader {

    public static MainController loadMainController(String filename)
    {
        FileInputStream fs = null;
        MainController mc =null;
        try {
             fs = new FileInputStream(ObjectWriter.ObjectDirectory+"/"+filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream oi = new ObjectInputStream(fs);
            try {
                mc =(MainController) oi.readObject();
                oi.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mc;
    }
    public static Object loadObject(String filename)
    {
        FileInputStream fs = null;
        ArrayList<String> s = null;
        try {
            fs = new FileInputStream(ObjectWriter.ObjectDirectory+"/"+filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream oi = new ObjectInputStream(fs);
            try {
                s =(ArrayList<String>) oi.readObject();
                oi.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static ArrayList<String> loadMC(String filename)
    {
        FileInputStream fs = null;
        ArrayList<String> s = null;
        try {
            fs = new FileInputStream(ObjectWriter.ObjectDirectory+"/"+filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream oi = new ObjectInputStream(fs);
            try {
                s =(ArrayList<String>) oi.readObject();
                oi.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
