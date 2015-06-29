package com.example.user.simplelife;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Smith on 2015/6/23.
 */
public class ObjectWriter {
    public static final String ObjectDirectory ="sdcard/";
    public static void WriteAppliance(MainController src,String filename) {

        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(ObjectDirectory+"/"+filename);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(fs);
            os.writeObject(src);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteMC(ArrayList<String> src,String filename) {

        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(ObjectDirectory+"/"+filename);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(fs);
            os.writeObject(src);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void WriteObject(Object src,String filename){
        FileOutputStream fs =null;
        try
        {
            fs = new FileOutputStream(ObjectDirectory+"/"+filename);
        }
        catch (FileNotFoundException ex)
        {

        }
        ObjectOutputStream outs = null;
        try
        {
            outs = new ObjectOutputStream(fs);
            outs.writeObject(src);
            outs.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
