/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

import org.dom4j.*;
import org.dom4j.io.*;
/**
 *
 * @author Smith
 */
public class ControllerSocketTable
{
    private HashMap<String,Socket> _table;
    public ControllerSocketTable(HashMap<String,Socket> table)
    {
        this._table = table;
    }
    public void SaveXml()
    {
        Document doc =  DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        for(String id : _table.keySet())
        {
            Element ele = root.addElement("MController");
            ele.addAttribute("Name", id);
        }
        try
        {
            FileWriter fw = new FileWriter(ControllerSocketTableCreator.ControllerSocketTablePath);
            OutputFormat format = new OutputFormat("  ", true, "utf-8");
            XMLWriter xw =new XMLWriter(fw, format);
            xw.write(doc);
            xw.close();
        } catch (IOException ex)
        {
            System.out.println("Saving CS table error");
        }
    }
    public void print()
    {
        int i =1;
        System.out.println("---------Controller Socket Table---------");
        for(String key : this._table.keySet())
        {
            Socket s = this._table.get(key);
            if(s!=null)
            {
                System.out.println(i++ + ". Controller: "+key+" "+s.getInetAddress());
            }
            else
            {
                System.out.println(i++ + ". Controller: "+key+" ");
            }
        }
    }
    public void addAuthenticationController(String MCID)
    {
        this._table.put(MCID, null);
    }
    public String ControllerConnect(String ID , Socket s)
    {
        Set<String> ids = this._table.keySet();
        if(ids.contains(ID))
        {
            this._table.put(ID, s);
            return "R008";
        }
        else
        {
            return "R009";
        }
    }
    public Socket getControllerSocket(String MCID)
    {
        return this._table.get(MCID);
    }
}
