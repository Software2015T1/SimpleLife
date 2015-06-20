package com.example.user.simplelife;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddFavoriteActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView listView;
    ArrayList<Expandable_Parent> expListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorite);

        listView = (ExpandableListView) findViewById(R.id.expandableListView);
        expListItems = prepareListData();
        listAdapter = new ExpandableListAdapter(AddFavoriteActivity.this, expListItems);
        listView.setAdapter(listAdapter);
    }

    private ArrayList<Expandable_Parent> prepareListData() {

        String parentNames[] = {
               "Light", "Air Conditioner", "Television", "Other"
        };

        int images[] = {
                R.drawable.light_icon, R.drawable.air_icon, R.drawable.tv_icon, R.drawable.other_icon
        };

        String childNames[] = {
                "test"
        };

        ArrayList<Expandable_Parent> list = new ArrayList<>();
        ArrayList<Expandable_Child> chList;
        Expandable_Parent parent = new Expandable_Parent();
        for(int image : images) {
            parent.setImage(image);
        }
        for (String parentName : parentNames) {
            parent.setName(parentName);
            chList = new ArrayList<>();
            for(int j = 0;j < childNames.length; j++) {
                Expandable_Child child = new Expandable_Child();
                child.setName(childNames[j]);
                chList.add(child);
            }
            parent.setItems(chList);
            list.add(parent);
        }

        return list;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
