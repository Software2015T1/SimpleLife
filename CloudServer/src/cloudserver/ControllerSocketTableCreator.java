/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.File;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import org.dom4j.io.*;
import org.dom4j.*;

/**
 *
 * @author Smith
 */
public class ControllerSocketTableCreator
{
    public static String ControllerSocketTablePath = "CSTABLE.xml";
    public static ControllerSocketTable createCSTable()
    {
        HashMap<String,Socket> table =new HashMap<>();
        if(loadXml(table))
        {
            System.out.println("Loading Controller Socket table form: "+ ControllerSocketTablePath);
            return new ControllerSocketTable(table);
        }
        else
        {
            System.out.println("CS table not found, create new table...");
            return new ControllerSocketTable(new HashMap<String, Socket>());
        }
    }
     private static boolean loadXml(HashMap<String,Socket> _table)
     {
         if(new File(ControllerSocketTablePath).exists()==false)return false;
         SAXReader reader =new SAXReader();
        try
        {
            Document doc = reader.read(ControllerSocketTablePath);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for(Element ele : list)
            {
                String name = ele.attributeValue("Name");
                _table.put(name, null);
            }
            
        } catch (DocumentException ex)
        {
            System.out.println(ex.toString());
            return false;
        }
        return true;
         
     }
}
