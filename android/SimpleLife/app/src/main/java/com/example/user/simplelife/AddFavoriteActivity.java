package com.example.user.simplelife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddFavoriteActivity extends Activity {

    ExpandListAdapter listAdapter;
    ExpandableListView listView;
    ArrayList<Expandable_Parent> expListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorite);

        listView = (ExpandableListView) findViewById(R.id.expandableListView);
        expListItems = prepareListData();
        listAdapter = new ExpandListAdapter (AddFavoriteActivity.this, expListItems);
        listView.setAdapter(listAdapter);

        ImageButton btnBack = (ImageButton) findViewById(R.id.ibtnBack_addfavorite);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFavoriteActivity.this, ApplianceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Expandable_Parent> prepareListData() {

        String parentNames[] = {
               "Light", "Air Conditioner", "Television", "Other"
        };

        int images[] = {
                R.drawable.light_icon, R.drawable.air_icon, R.drawable.tv_icon, R.drawable.other_icon_white
        };

        //String childNames[] = {
        //        "living room","kitchen"
        //};
        //ArrayList<String> childNames = new ArrayList<>();



        ArrayList<Expandable_Parent> list = new ArrayList<>();
        ArrayList<String> mclist = ObjectReader.loadMC("MC_ID");
        ArrayList<Appliance> appliances = new ArrayList<>();
        for(int i=0;i<mclist.size();i++)
        {
            String mcName = mclist.get(i);
            MainController mc = ObjectReader.loadMainController(mcName);
            ArrayList<Appliance> temp = mc.getAppliances();
            for(int j=0;j<temp.size();j++)appliances.add(j,temp.get(j));
        }
        for (int i = 0; i < parentNames.length ;i++) {
            Expandable_Parent parent = new Expandable_Parent();
            ArrayList<Expandable_Child> chList;
            parent.setImage(images[i]);
            parent.setName(parentNames[i]);
            chList = new ArrayList<>();
            String pName = parentNames[i];
            ArrayList<String> childNames = null;
            if(pName.equals("Light"))
            {
                ArrayList<String> tempS = new ArrayList<>();
                for(int k=0;k<appliances.size();k++)
                {
                    Appliance app = appliances.get(k);
                    if(app.getType().equals("Light"))tempS.add(app.getName());
                }
                childNames = tempS;
            }
            else if(pName.equals("Air Conditioner"))
            {
                ArrayList<String> tempS = new ArrayList<>();
                for(int k=0;k<appliances.size();k++)
                {
                    Appliance app = appliances.get(k);
                    if(app.getType().equals("AC"))tempS.add(app.getName());
                }
                childNames = tempS;
            }
            else if(pName.equals("Television"))
            {
                ArrayList<String> tempS = new ArrayList<>();
                for(int k=0;k<appliances.size();k++)
                {
                    Appliance app = appliances.get(k);
                    if(app.getType().equals("TV"))tempS.add(app.getName());
                }
                childNames = tempS;
            }
            else if(pName.equals("Other"))
            {
                ArrayList<String> tempS = new ArrayList<>();
                for(int k=0;k<appliances.size();k++)
                {
                    Appliance app = appliances.get(k);
                    if(app.getType().equals("Other"))tempS.add(app.getName());
                }
                childNames = tempS;
            }
            for(int j = 0; j < childNames.size(); j++) {
                Expandable_Child child = new Expandable_Child();
                child.setName(childNames.get(j));
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

