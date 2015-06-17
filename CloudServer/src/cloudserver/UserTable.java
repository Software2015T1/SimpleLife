/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;
import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.dom4j.*;
import org.dom4j.io.*;
/**
 *
 * @author Smith
 */
public class UserTable
{
    private HashMap<String,UserInformation> _userTable;
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
            Element eleUser =root.addElement(key);
            UserInformation info = _userTable.get(key);
            Element ele = eleUser.addElement("Password");
            ele.addText(info.getPassword());
            Element ele2 = eleUser.addElement("ControllerID");
            ele2.addText(info.getControllerID());
        }
        try
        {
            FileWriter fw = new FileWriter(UserTableCreator.UserTablePath);
            OutputFormat format = new OutputFormat();
            format.setIndentSize(2);
            format.setNewlines(true);
            XMLWriter xw = new XMLWriter(fw, format);
            xw.write(doc);
            xw.close();
        } catch (IOException ex)
        {
           System.out.println("Saving user table error");
        }
        System.out.println("Saving user table successfule!");
    }
    public void print()
    {
        for(String key : _userTable.keySet())
        {
            System.out.println("user name: "+key);
        }
    }
    
}
