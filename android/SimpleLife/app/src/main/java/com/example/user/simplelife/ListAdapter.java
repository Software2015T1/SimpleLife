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
public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private static LayoutInflater inflater = null;

    public ListAdapter(Context c) {
        mContext = c;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return icons_IDs.length;
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

        view = inflater.inflate(R.layout.addproduct_list_item, null);
        TextView text = (TextView) view.findViewById(R.id.textView_listitem);
        ImageView image1 = (ImageView) view.findViewById(R.id.imageView_listitem1);
        ImageView image2 = (ImageView) view.findViewById(R.id.imageView_listitem2);

        image1.setImageResource(icons_IDs[position]);
        text.setText(names[position]);
        image2.setImageResource(R.drawable.right_icon2);

        return view;
    }

    private Integer[] icons_IDs = {
            R.drawable.maincontroller_icon, R.drawable.light_icon,
            R.drawable.air_icon, R.drawable.tv_icon, R.drawable.other_icon
    };

    private String[] names = new String[] {
            "Main Controller", "Light", "Air Conditioner", "Television", "Other"
    };
}
