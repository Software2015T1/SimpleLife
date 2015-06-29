package com.example.user.simplelife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2015/6/19.
 */
public class Favorite_ListAdapter extends BaseAdapter {
    private Context mContext;
    private static LayoutInflater inflater = null;
    private ArrayList<Appliance> appliances;
    public Favorite_ListAdapter(Context c) {
        mContext = c;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ArrayList<String> mclist = ObjectReader.loadMC("MC_ID");
        if(mclist.size()>0)appliances = ObjectReader.loadMainController(mclist.get(0)).getAppliances();
    }

    @Override
    public int getCount() {
        return deviceName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        view = inflater.inflate(R.layout.favorite_list_item, null);
        TextView text = (TextView) view.findViewById(R.id.textView_listitem_favorite);
        ImageView image1 = (ImageView) view.findViewById(R.id.imageView_listitem_favorite);
        String deviceN = deviceName.get(position);
        //image1.setImageResource(icons_IDs[position]);
        //text.setText(names[position]);
        text.setText(deviceN);
        if(appliances!=null)
        {
            for(int i=0;i<appliances.size();i++)
            {
                Appliance app = appliances.get(i);
                if(app.getName().equals(deviceN))
                {
                    if(app.getType().equals("Light"))
                    {
                        image1.setImageResource(R.drawable.light_icon);
                    }
                    else if(app.getType().equals("TV"))
                    {
                        image1.setImageResource(R.drawable.tv_icon);
                    }
                    else if(app.getType().equals("AC"))
                    {
                        image1.setImageResource(R.drawable.air_icon);
                    }
                    else if(app.getType().equals("Other"))
                    {
                        image1.setImageResource(R.drawable.other_icon);
                    }
                }
            }

        }
        final Switch _switch =(Switch) view.findViewById(R.id.switch1);
        _switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {

                }
                else
                {

                }
            }
        });

        return view;
    }

    private Integer[] icons_IDs = {
            R.drawable.air_icon,R.drawable.light_icon
    };

    private String[] names = new String[] {
            "I'm a boy","you are a girl"
    };

    private ArrayList<String> deviceName = new ArrayList<>();
    public void setDeviceName(ArrayList<String> list)
    {
        this.deviceName = list;
    }
}

