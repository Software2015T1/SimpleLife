package com.example.user.simplelife;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class LightActivity extends ActionBarActivity {

    private ListView listView;
    private Light_ListAdapter adapter;
    private Light appliance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        appliance = (Light)getIntent().getSerializableExtra("device");
        TextView nameText = (TextView)findViewById(R.id.textName_light);
        nameText.setText(appliance.getName());
        TextView contentText = (TextView)findViewById(R.id.textContent_light);
        contentText.setText("Connected to \n" + appliance.getMainControllerName());

        listView = (ListView) findViewById(R.id.listView_light);
        adapter = new Light_ListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Intent intent = new Intent(LightActivity.this, TimeSettingActivity.class);
                    startActivity(intent);
                }
                if(position == 1) {
                    Intent intent = new Intent(LightActivity.this, ProximitySettingActivity.class);
                    startActivity(intent);
                }
                if(position == 2) {
                    Intent intent = new Intent(LightActivity.this, EnergySaver.class);
                    startActivity(intent);
                }

            }
        });

        ImageButton backButton = (ImageButton)findViewById(R.id.ibtnBack_light);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LightActivity.this, ApplianceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_light);
        if(appliance.getState()){
            btnOn.setImageResource(R.drawable.circle_light_yellow);
        }
        else{
            btnOn.setImageResource(R.drawable.circle_light);
        }
        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommandCreator cc = new CommandCreator();
                ArrayList<String> strings = new ArrayList<String>();
                strings.add("/ControlAppliance");
                strings.add(UserProfile.email);
                strings.add(UserProfile.password);
                strings.add(appliance.getMainControllerID());
                strings.add(appliance.getType());
                strings.add(appliance.getDeviceID());
                if(appliance.getState()){
                    strings.add("on");
                    appliance.setState(false);
                    ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_light);
                    btnOn.setImageResource(R.drawable.circle_light);
                }
                else{
                    strings.add("off");
                    appliance.setState(true);
                    ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_light);
                    btnOn.setImageResource(R.drawable.circle_light_yellow);
                }
                cc.createCommand(strings);
                cc.sendToServer();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_light, menu);
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
