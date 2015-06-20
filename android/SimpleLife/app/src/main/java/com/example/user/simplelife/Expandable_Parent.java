package com.example.user.simplelife;

import java.util.ArrayList;

/**
 * Created by User on 2015/6/20.
 */
public class Expandable_Parent {
    private String name;
    private int Image;

    // ArrayList to store child objects
    private ArrayList<Expandable_Child> children;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int Image) {
        this.Image = Image;
    }

    // ArrayList to store child objects
    public ArrayList<Expandable_Child> getItems()
    {
        return children;
    }

    public void setItems(ArrayList<Expandable_Child> children)
    {
        this.children = children;
    }
}


