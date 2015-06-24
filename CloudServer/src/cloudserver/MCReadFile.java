/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Smith
 */
public class MCReadFile extends TimerTask
{
    private String filename;
    private Timer timer;
    private static int count=0;
    
    public MCReadFile(String filename)
    {
        this.filename = filename;
        timer = new Timer();
    }
    public void start()
    {
       timer.schedule(this,100 ,1000);
    }
    @Override
    public void run()
    {
        FileReader fr = null;
        BufferedReader br ;
        try
        {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            try
            {
                int nowCnt =Integer.parseInt(br.readLine());
                //System.out.println(nowCnt);
                if(nowCnt>=count)
                {
                    //do somthing in here
                    String cmd  =br.readLine();
                    System.out.println(cmd);
                    count++;
                }
            } catch (IOException ex)
            {
                System.out.println(ex);
            }
        } catch (FileNotFoundException ex)
        {
           
        } finally
        {
            try
            {
                fr.close();
            } catch (IOException ex)
            {
                
            }
        }
    }
    public static void main(String[] args)
    {
        String filename ="D:\\download\\socket\\JavatoC.txt";
        MCWriteFile mwrite = new MCWriteFile(filename);
        //mwrite.write("hello");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {            
            String cmd=null;
            try
            {
                cmd = br.readLine();
            } catch (IOException ex)
            {
                Logger.getLogger(MCReadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            mwrite.write(cmd);
        }
        //MCReadFile reader  = new MCReadFile(filename);
        //reader.start();
    }
    
}
