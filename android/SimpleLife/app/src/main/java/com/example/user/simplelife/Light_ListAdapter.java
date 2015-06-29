package com.example.user.simplelife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 2015/6/18.
 */
public class Light_ListAdapter extends BaseAdapter {

    private Context mContext;
    private static LayoutInflater inflater = null;

    public Light_ListAdapter(Context c) {
        mContext = c;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.length;
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

        view = inflater.inflate(R.layout.light_list_item, null);
        TextView text = (TextView) view.findViewById(R.id.text_listitem_light);
        ImageView image2 = (ImageView) view.findViewById(R.id.image_listitem_light);

        text.setText(list[position]);
        image2.setImageResource(R.drawable.right_icon2);

        return view;
    }

    private String[] list = new String[] {
            "Time Setting","Proximity Setting","Energy Saver"
    };
    public void setList(int index,String text)
    {
        list[index]= text;
    }
}
