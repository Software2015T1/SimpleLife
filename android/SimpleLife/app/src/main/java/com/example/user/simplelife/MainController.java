package com.example.user.simplelife;
import java.util.ArrayList;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class MainController extends Device{
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
}
