package com.example.user.simplelife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by User on 2015/6/20.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Expandable_Parent> groups;

    public ExpandableListAdapter(Context context, ArrayList<Expandable_Parent> groups) {
        this.context = context;
        this.groups = groups;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Expandable_Child> childList = groups.get(groupPosition).getItems();
        return childList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
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
        txtChild.setText(child.getName().toString());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Expandable_Child> childList = groups.get(groupPosition).getItems();
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