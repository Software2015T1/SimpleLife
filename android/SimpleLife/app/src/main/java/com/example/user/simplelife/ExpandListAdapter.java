package com.example.user.simplelife;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by User on 2015/6/20.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Expandable_Parent> groups;

    public ExpandListAdapter(Context context, ArrayList<Expandable_Parent> groups) {
        this.context = context;
        this.groups = groups;
    }

    public void addItem(Expandable_Child item, Expandable_Parent group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
        int index = groups.indexOf(group);
        ArrayList<Expandable_Child> ch = groups.get(index).getItems();
        ch.add(item);
        groups.get(index).setItems(ch);
    }



    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Expandable_Child> childList = groups.get(groupPosition).getItems();
        Log.v("fuck you", "in get child");
        return childList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.v("fuck you", "in get child id");
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Expandable_Child child = (Expandable_Child) getChild(groupPosition, childPosition);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_childrow, null);
        }
        TextView txtChild = (TextView) convertView.findViewById(R.id.textWhere_addfavorite);
        txtChild.setText(child.getName());
        Log.v("fuck you", child.getName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Expandable_Child> childList = groups.get(groupPosition).getItems();
        Log.v("fuck you","in get child count");
        return childList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parentView) {
        Expandable_Parent group = (Expandable_Parent) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_grouprow, null);
        }
        TextView txtParent = (TextView) convertView.findViewById(R.id.textName_addFavorite);
        txtParent.setText(group.getName());
        ImageView imageParent = (ImageView) convertView.findViewById(R.id.imageIcon_addFavorite);
        imageParent.setImageResource(group.getImage());
        Log.v("fuck you",group.getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}