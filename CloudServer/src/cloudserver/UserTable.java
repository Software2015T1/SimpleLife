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
public class UserTable
{
    private final HashMap<String,UserInformation> _userTable;
    public UserTable(HashMap<String,UserInformation> _table)
    {
        this._userTable = _table;
    }
    public void SaveXml()
    {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");

        for(String key:_userTable.keySet())
        {
            Element eleUser =root.addElement("User");
            eleUser.addAttribute("Name", key);
            UserInformation info = _userTable.get(key);
            Element ele = eleUser.addElement("Password");
            ele.addText(info.getPassword());
            Element ele2 = eleUser.addElement("ControllerID");
            ele2.addText(info.getControllerID());
        }
        try
        {
            FileWriter fw = new FileWriter(UserTableCreator.UserTablePath);
            OutputFormat format = new OutputFormat("  ",true,"utf-8");
            XMLWriter xw = new XMLWriter(fw, format);
            xw.write(doc);
            xw.close();
        } catch (IOException ex)
        {
           System.out.println("Saving user table error");
        }
        
    }
    public void print()
    {
        int i=1;
        System.out.println("----------------User Table--------------");
        for(String key : _userTable.keySet())
        {
            UserInformation info = _userTable.get(key);
            Socket s = info.getUserSocket();
            if(s!=null)
            {
                System.out.println((i++)+". user name: "+key+" "+info.getControllerID()+" "+s.getInetAddress().toString());
            }
            else
            {
                System.out.println((i++)+". user name: "+key+" "+info.getControllerID());
            }
        }
    }
    
    public String add(String email,UserInformation info)
    {
        Set<String> emails = _userTable.keySet();
        if(emails.contains(email))return "R00";
        _userTable.put(email, info);
        return "R01";
        
    }
    public String authenticate(String username,String password,Socket client)
    {
        Set<String> users = this._userTable.keySet();
        if(users.contains(username))
        {
            UserInformation info = this._userTable.get(username);
            if(info.getPassword().equals(password))
            {
                info.setSocket(client);
                return "R002";
            }
            else
            {
                return "R004";
            }
        }
        else
        {
           return "R003"; 
        }
    }
    public boolean authenticate(String username,String password)
    {
        Set<String> users = this._userTable.keySet();
        if(users.contains(username))
        {
            UserInformation info = this._userTable.get(username);
            if(info.getPassword().equals(password))
            {
                return true;
            }

        }
        return false;
    }
    public String AddMC(String username,String MCID)
    {
        UserInformation info = this._userTable.get(username);
        info.setMController(MCID);
        return "R005";
    }
    public String getControllerID(String username)
    {
        return this._userTable.get(username).getControllerID();
    }
    
}
