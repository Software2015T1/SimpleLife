package com.example.user.simplelife;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class MainController extends Device implements Serializable{
    private ArrayList<Appliance> appliances;
    private String mainControlerID;
    private String address;
    private int image;
    public MainController(String mainControlerID,String name,String address) {
        this.type = "Main";
        this.mainControlerID = mainControlerID;
        this.name = name;
        this.address = address;
        this.appliances = new ArrayList<Appliance>();
        this.image = R.drawable.circle_main;
    }

    public MainController(){
        this.type = "Main";
        this.image = R.drawable.circle_main;
        this.appliances = new ArrayList<Appliance>();
    }

    public String getMainControlerID() {
        return mainControlerID;
    }

    public void setMainControlerID(String mainControlerID) {
        this.mainControlerID = mainControlerID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Appliance> getAppliances() {
        return appliances;
    }

    public void addAppliance(Appliance appliance) {
        this.appliances.add(appliance);
    }

    public void removeAppliance(Appliance appliance) {
        this.appliances.remove(appliance);
    }

    public int getImage(){
        return this.image;
    }

    public String getAddress(){
        return address;
    }

    public void setAppliances(ArrayList<Appliance> appliances){
        this.appliances = appliances;
    }

    public void setAppliance(Appliance app)
    {
       String deviceID = app.getDeviceID();
       for(int i =0 ;i<this.appliances.size();i++)
       {
           if(this.appliances.get(i).deviceID.equals(deviceID))
           {
               this.appliances.set(i,app);
               break;
           }
       }
    }

    public Appliance getApplianceByDeviceID(String deviceID)
    {
        for(int i=0;i<this.appliances.size();i++)
        {
            Appliance app = this.appliances.get(i);
            if(app.getDeviceID().equals(deviceID))
            {
                return  app;
            }
        }
        return null;
    }
}
