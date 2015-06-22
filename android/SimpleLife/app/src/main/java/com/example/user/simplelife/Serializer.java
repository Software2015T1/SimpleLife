package com.example.user.simplelife;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by User on 2015/6/22.
 */
public class Serializer {

    public static void main(String arg[]) throws IOException {

        Device object1 = new Device();
        FileOutputStream fs = new FileOutputStream("device.ser");
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(object1);
        os.close();
    }
}
