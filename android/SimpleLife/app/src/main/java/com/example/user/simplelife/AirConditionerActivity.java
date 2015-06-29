package com.example.user.simplelife;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class AirConditionerActivity extends ActionBarActivity {
    private static int PROXIMITY_CHANGE=0;
    private static int TIME_SETTING=1;
    private ListView listView;
    private Air_ListAdapter adapter;
    private AirConditioner appliance;
    private int[] numID = {R.drawable.numbers_0,R.drawable.numbers_1,R.drawable.numbers_2,
                            R.drawable.numbers_3,R.drawable.numbers_4,R.drawable.numbers_5,
                            R.drawable.numbers_6,R.drawable.numbers_7,R.drawable.numbers_8,R.drawable.numbers_9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioner);
        appliance = (AirConditioner)getIntent().getSerializableExtra("device");
        setTemperatureView();
        setStrengthView();
        setDirectionView();
        TextView nameText = (TextView)findViewById(R.id.textName_air);
        nameText.setText(appliance.getName());
        TextView contentText = (TextView)findViewById(R.id.textContent_air);
        contentText.setText("Connected to \n" + appliance.getMainControllerName());

        listView = (ListView) findViewById(R.id.listView_air);
        adapter = new Air_ListAdapter(this);

        TimeSetting timeSetting = appliance.getTimeSetting();
        ProximitySetting proxSetting = appliance.getProximitySetting();
        if(timeSetting!=null)
        {
            //change text in listview
            String[] minute = setMinute(timeSetting);
            String text = "Turns on every "+ timeSetting.getStartTime().getDate() + "\n" +
                    timeSetting.getStartTime().getHour() + " : " + minute[0] + " to " +
                    timeSetting.getEndTime().getDate() + " " + timeSetting.getEndTime().getHour() + " : " + minute[1];
            adapter.setList(0,text);

        }
        if(proxSetting!=null)
        {
            //do something
            String text ="Turns on when you are " + proxSetting.getDistance() + " from home.";
            adapter.setList(1,text);

        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent intent = new Intent(AirConditionerActivity.this, TimeSettingActivity.class);
                        intent.putExtra("device", appliance);
                        //startActivity(intent);
                        startActivityForResult(intent,TIME_SETTING);
                        break;
                    case 1:
                        intent = new Intent(AirConditionerActivity.this,ProximitySettingActivity.class);
                        intent.putExtra("device", appliance);
                        //startActivity(intent);
                        startActivityForResult(intent,PROXIMITY_CHANGE);
                        break;
                    case 2:
                        intent = new Intent(AirConditionerActivity.this,EnergySaverActivity.class);
                        intent.putExtra("device", appliance);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(AirConditionerActivity.this,IdealTemperatureActivity.class);
                        intent.putExtra("device", appliance);
                        startActivity(intent);
                        break;
                }
            }
        });

        ImageButton backButton = (ImageButton)findViewById(R.id.ibtnBack_air);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_air);
        if(appliance.getState()){
            btnOn.setImageResource(R.drawable.circle_air_yellow);
        }
        else{
            btnOn.setImageResource(R.drawable.circle_air);
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
                strings.add("onoff");
                if (appliance.getState()) {
                    strings.add("off");
                    appliance.setState(false);
                    ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_air);
                    btnOn.setImageResource(R.drawable.circle_air);
                } else {
                    strings.add("on");
                    appliance.setState(true);
                    ImageButton btnOn = (ImageButton) findViewById(R.id.ibtnCircle_air);
                    btnOn.setImageResource(R.drawable.circle_air_yellow);
                }
                cc.createCommand(strings);
                cc.sendToServer();
            }
        });

        ImageButton btnUp = (ImageButton) findViewById(R.id.ibtnUp_air);
        btnUp.setOnClickListener(new View.OnClickListener() {
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
                strings.add("temperature");
                int temp = appliance.getTemperature()+1;
                appliance.setTemperature(temp);
                strings.add(Integer.toString(temp));
                setTemperatureView();
                cc.createCommand(strings);
                cc.sendToServer();
            }
        });

        ImageButton btnDown = (ImageButton) findViewById(R.id.ibtnDown_air);
        btnDown.setOnClickListener(new View.OnClickListener() {
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
                strings.add("temperature");
                int temp = appliance.getTemperature()-1;
                appliance.setTemperature(temp);
                strings.add(Integer.toString(temp));
                setTemperatureView();
                cc.createCommand(strings);
                cc.sendToServer();
            }
        });

        ImageButton btnStrength = (ImageButton) findViewById(R.id.ibtnStrength_air);
        btnStrength.setOnClickListener(new View.OnClickListener() {
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
                strings.add("strength");
                appliance.changeStrength();
                strings.add(appliance.getStrength());
                setStrengthView();
                cc.createCommand(strings);
                cc.sendToServer();
            }
        });

        ImageButton btnDirection = (ImageButton) findViewById(R.id.ibtnDirection_air);
        btnDirection.setOnClickListener(new View.OnClickListener() {
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
                strings.add("direction");
                appliance.changeDirection();
                strings.add(appliance.getDirection());
                setDirectionView();
                cc.createCommand(strings);
                cc.sendToServer();
            }
        });
    }

    private String[] setMinute(TimeSetting timeSetting) {
        String[] strings = new String[2];
        if(timeSetting.getStartTime().getMinute() < 10) {
            strings[0] = "0" + timeSetting.getStartTime().getMinute();
        }
        else {
            strings[0] = Integer.toString(timeSetting.getStartTime().getMinute());
        }
        if(timeSetting.getEndTime().getMinute() < 10) {
            strings[1] = "0" + timeSetting.getEndTime().getMinute();
        }
        else {
            strings[1] = Integer.toString(timeSetting.getEndTime().getMinute());
        }
        return strings;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_air_conditioner, menu);
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

    private void setTemperatureView(){
        int temp = appliance.getTemperature();
        int first = temp/10;
        int second = temp%10;

        ImageView digit1 = (ImageView)findViewById(R.id.imageNumber1_air);
        digit1.setImageResource(numID[first]);
        ImageView digit2 = (ImageView)findViewById(R.id.imageNumber2_air);
        digit2.setImageResource(numID[second]);
    }

    private void setStrengthView(){
        String strength = appliance.getStrength();
        ImageView s1 = (ImageView)findViewById(R.id.imageStrength1_air);
        ImageView s2 = (ImageView)findViewById(R.id.imageStrength2_air);
        ImageView s3 = (ImageView)findViewById(R.id.imageStrength3_air);
        ImageView auto = (ImageView)findViewById(R.id.imageAuto_strength_air);

        if(strength.equals("auto")){
            auto.setVisibility(View.VISIBLE);
            s1.setVisibility(View.GONE);
            s2.setVisibility(View.GONE);
            s3.setVisibility(View.GONE);
        }
        else{
            auto.setVisibility(View.GONE);
            if(strength.equals("weak")){
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.GONE);
                s3.setVisibility(View.GONE);
            }
            else if(strength.equals("normal")){
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.GONE);
            }
            else{
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setDirectionView(){
        String direction = appliance.getDirection();
        ImageView d = (ImageView)findViewById(R.id.image_Icon_Direction_air);
        d.setVisibility(View.VISIBLE);
        ImageView auto = (ImageView)findViewById(R.id.imageAuto_direction_air);
        auto.setVisibility(View.VISIBLE);
        if(direction.equals("auto")){
            d.setVisibility(View.GONE);
        }
        else if(direction.equals("0")){
            d.setImageResource(R.drawable.arrow_right_white);
            auto.setVisibility(View.GONE);
        }
        else if(direction.equals("45")){
            d.setImageResource(R.drawable.arrow_southeast_white);
            auto.setVisibility(View.GONE);
        }
        else if(direction.equals("90")){
            d.setImageResource(R.drawable.arrow_down_white);
            auto.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        View v = null;
        if(requestCode==PROXIMITY_CHANGE)
        {   v = listView.getChildAt(1-listView.getFirstVisiblePosition());}
        else if(requestCode==TIME_SETTING)
        {   v=listView.getChildAt(0-listView.getFirstVisiblePosition());}
        if(v==null)return;
        Bundle bundle = null;
        switch (resultCode)
        {
            case Activity.RESULT_OK:
                String text = data.getStringExtra(getString(R.string.Get_ListView_Text));
                ((TextView)v.findViewById(R.id.text_listitem_light)).setText(text);
                break;

        }
    }
}
