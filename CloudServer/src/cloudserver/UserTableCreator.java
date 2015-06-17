/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.dom4j.*;
import org.dom4j.io.*;
/**
 *
 * @author Smith
 */

public class UserTableCreator
{
    public static final String UserTablePath = "USERTABLE.xml";
    
    public static UserTable createUserTable()
    {
       HashMap<String,UserInformation> _table=new HashMap<>();
      if(loadXml(_table))
      {
          System.out.println("Loading user table from: "+ UserTablePath);
      }
      else
      {
          System.out.println("user table not found, create new user table...");
          _table = new HashMap<>();
          return  new UserTable(_table);
      }
       UserTable usertable = new UserTable(_table);
       return usertable;
    }
    private static boolean loadXml(HashMap<String,UserInformation> _table)
    {
        try
        {
           SAXReader reader = new SAXReader();
           Document doc = reader.read(UserTablePath);
           Element root = doc.getRootElement();
           List<Element> list = root.elements();
           for(Element user:list)
           {
                Element password = user.element("Password");
                Element controllerID = user.element("ControllerID");
                _table.put(user.getName(),new UserInformation(password.getText(),controllerID.getText(), null));
           }

           return true;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return false;
    }
}
