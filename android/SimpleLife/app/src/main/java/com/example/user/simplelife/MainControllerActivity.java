package com.example.user.simplelife;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainControllerActivity extends ActionBarActivity {

    private GridView gridView;
    private SimpleAdapter adapter;
    private MainController main;
    private List<Map<String, Object>> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_controller);
        setItem();
        adapter = new SimpleAdapter(MainControllerActivity.this,
                items, R.layout.grid_item, new String[]{"image", "text"},
                new int[]{R.id.image, R.id.text});

        gridView = (GridView) findViewById(R.id.gridView_Main);
        gridView.setNumColumns(3);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        TextView nameText = (TextView)findViewById(R.id.textName_Main);
        nameText.setText(main.getName());

        TextView contentText = (TextView)findViewById(R.id.textContent_Main);
        contentText.setText("ID Number "+main.getMainControlerID()+"\n"+main.getAddress());

        ImageButton backButton = (ImageButton)findViewById(R.id.ibtnBack_maincontroller);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_controller, menu);
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

    public void setItem(){
        main = (MainController)getIntent().getSerializableExtra("device");
        Map<String, Object> item = new HashMap<>();
        item.put("image",main.getImage());
        item.put("text", main.getName());
        items.add(item);
        ArrayList<Appliance> appliances = main.getAppliances();

        for (int j = 0 ; j < appliances.size(); j++){
            Appliance appliance = appliances.get(j);
            item = new HashMap<>();
            item.put("image",appliance.getImage());
            item.put("text", appliance.getName());
            items.add(item);
        }
    }
}
