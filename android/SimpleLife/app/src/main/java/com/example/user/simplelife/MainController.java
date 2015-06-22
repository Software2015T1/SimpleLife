package com.example.user.simplelife;
import java.util.ArrayList;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class MainController {
    private ArrayList<Appliance> appliances;
    private String mainControlerID;

    public MainController(String mainControlerID) {
        this.mainControlerID = mainControlerID;
    }

    public String getMainControlerID() {
        return mainControlerID;
    }

    public void setMainControlerID(String mainControlerID) {
        this.mainControlerID = mainControlerID;
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
}
