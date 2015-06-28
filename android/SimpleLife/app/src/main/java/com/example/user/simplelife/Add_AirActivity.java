package com.example.user.simplelife;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageButton;

import java.util.ArrayList;


public class Add_AirActivity extends Add_Activity {

    private AirConditioner appliance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__air);
        appliance = new AirConditioner();
        fragmentList = new ArrayList<Fragment>();
        index = 0;
        frame_num = 4;
        fragment_id = R.id.addAir_layout;

        for(int i = 0 ; i< frame_num ;i++){
            switch(i){
                case 0:
                    fragmentList.add(FragmentAddAir_step1.newInstance());
                    break;
                case 1:
                    fragmentList.add(FragmentAddAir_step2.newInstance());
                    break;
                case 2:
                    fragmentList.add(FragmentAddAir_step3.newInstance());
                    break;
                case 3:
                    fragmentList.add(FragmentAddAir_step4.newInstance());
                    break;
            }
        }
        changeFragment(fragmentList.get(index));

        ImageButton backButton = (ImageButton)findViewById(R.id.ibtnBack_AddOther);
        setBackButtonListener(backButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add__air, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Appliance getAppliance(){
        return this.appliance;
    }

    public void saveAppliance(){

        MainController main = ObjectReader.loadMainController(appliance.getMainControllerID());
        main.addAppliance(appliance);
        ObjectWriter.WriteAppliance(main,appliance.getMainControllerID());

        CommandCreator cc = new CommandCreator();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("/AddAppliance");
        strings.add(UserProfile.email);
        strings.add(UserProfile.password);
        strings.add(appliance.getMainControllerID());
        strings.add(appliance.getType());
        strings.add(appliance.getArduinoID());
        strings.add(appliance.getDeviceID());
        strings.add(appliance.getBrand());
        if(appliance.getState()){
            strings.add("off");
        }
        else{
            strings.add("on");
        }
        cc.createCommand(strings);
        cc.sendToServer();

        Intent intent = new Intent(Add_AirActivity.this, ApplianceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
