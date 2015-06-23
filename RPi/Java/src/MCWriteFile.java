/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author Smith
 */
public class MCWriteFile
{
    private String _filename;
    private static int count=0;
    public MCWriteFile(String filename)
    {
        this._filename =filename;
    }
    public void write(String cmd) 
    {
        BufferedWriter br = null;
        try
        {
            FileWriter fr = new FileWriter(_filename);
            br = new BufferedWriter(fr);
            br.write(String.valueOf(count));
            br.newLine();
            count++;
            br.write(cmd);
            br.flush();
        } catch (IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try
            {
                br.close();
            } catch (IOException ex)
            {
                
            }
        }
        
        
    }
}
