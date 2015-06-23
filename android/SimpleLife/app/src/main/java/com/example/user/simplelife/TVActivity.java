package com.example.user.simplelife;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class TVActivity extends ActionBarActivity {

    private TV appliance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        appliance = (TV)getIntent().getSerializableExtra("device");
        TextView nameText = (TextView)findViewById(R.id.textName_tv);
        nameText.setText(appliance.getName());
        TextView contentText = (TextView)findViewById(R.id.textContent_tv);
        contentText.setText("Connected to \n" + appliance.getMainControllerName());

        ImageButton backButton = (ImageButton)findViewById(R.id.ibtnBack_tv);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TVActivity.this, ApplianceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_tv);
        if(appliance.getState()){
            btnOn.setImageResource(R.drawable.circle_tv_yellow);
        }
        else{
            btnOn.setImageResource(R.drawable.circle_tv);
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
                if (appliance.getState()) {
                    strings.add("off");
                    appliance.setState(false);
                    ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_tv);
                    btnOn.setImageResource(R.drawable.circle_tv);
                } else {
                    strings.add("on");
                    appliance.setState(true);
                    ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_tv);
                    btnOn.setImageResource(R.drawable.circle_tv_yellow);
                }
                cc.createCommand(strings);
                cc.sendToServer();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tv, menu);
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
