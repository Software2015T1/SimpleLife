package com.example.user.simplelife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2015/6/19.
 */
public class Favorite_ListAdapter extends BaseAdapter {
    private Context mContext;
    private static LayoutInflater inflater = null;

    public Favorite_ListAdapter(Context c) {
        mContext = c;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        //image1.setImageResource(icons_IDs[position]);
        //text.setText(names[position]);
        text.setText(deviceName.get(position));
        //image1.setImageResource(R.drawable.air_icon);

        return view;
    }

    private Integer[] icons_IDs = {
            R.drawable.air_icon,R.drawable.light_icon
    };

    private String[] names = new String[] {
            "I'm a boy","you are a girl"
    };

    private ArrayList<String> deviceName;
    public void setDeviceName(ArrayList<String> list)
    {
        this.deviceName = list;
    }
}

