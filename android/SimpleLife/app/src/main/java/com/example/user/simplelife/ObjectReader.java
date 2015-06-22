package com.example.user.simplelife;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

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
}
