package com.example.user.simplelife;

/**
 * Created by User on 2015/6/20.
 */
public class Expandable_Child {
    private String name;
    private String checkedtype;
    private boolean checked;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCheckedtype()
    {
        return checkedtype;
    }

    public void setCheckedtype(String checkedtype)
    {
        this.checkedtype = checkedtype;
    }


    public boolean isChecked()
    {
        return checked;
    }
    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }
    public void toggle()
    {
        this.checked = !this.checked;
    }
}
