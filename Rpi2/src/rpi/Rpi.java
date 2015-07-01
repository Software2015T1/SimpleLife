/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpi;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
/**
 *
 * @author Smith
 */
public class Rpi
{

    /**
     * @param args the command line arguments
     */
    static SerialPort sport ;
    private static final String IP = "140.112.53.245";
    private static final int PORT = 1028;
    public static void main(String[] args)
    {
        // TODO code application logic here
        System.out.println("hello");
        sport = new SerialPort("/dev/ttyACM0");
        try
        {
            System.out.println("Port opened: "+sport.openPort());
            sport.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
            sport.addEventListener(new PortReader(),SerialPort.MASK_RXCHAR);
        } catch (SerialPortException ex)
        {
            System.out.println(ex.toString());
        }
        try
        {
            Socket _s = new Socket(IP,PORT);
            System.out.println("connect to server: " + IP);
            DataInputStream inputs = new DataInputStream(_s.getInputStream());
            DataOutputStream outs = new DataOutputStream(_s.getOutputStream());
            outs.writeUTF("/IRMCLogin [MCID]");
            while(true)
            {
                String rline = inputs.readUTF();
                System.out.println(rline);
                String[] lines = rline.split(" ");
                if(lines[0].equals("/IRControl"))
                {
                    try {
                    if(lines[1].equals("on"))
                    {
                        sport.writeInt('o');
                        System.out.println("on");
                    }
                    if(lines[1].equals("off"))
                    {
                        sport.writeInt('f');
                        System.out.println("off");
                    }
                    } catch (SerialPortException ex) {
                       System.out.println(ex.toString());
                    }
                }
            }
            
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
  
        
    }
    private static class PortReader implements SerialPortEventListener
    {

        public PortReader()
        {
        }

        @Override
        public void serialEvent(SerialPortEvent spe)
        {
            if(spe.getEventValue()>0 && spe.isRXCHAR())
            {
                try
                {
                    String recvData = sport.readString();
                    ///String printString  = recvData.split("!")[0];
                    System.out.println(recvData);
                } catch (Exception e)
                {
                    System.out.println(e.toString());
                }
            }
        }
    }
}
