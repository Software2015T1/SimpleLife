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


public class Add_LightActivity extends Add_Activity{

    private Light appliance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__light);
        appliance = new Light();

        fragmentList = new ArrayList<Fragment>();
        index = 0;
        frame_num = 2;
        fragment_id =R.id.addLight_layout;
        for(int i = 0 ; i< frame_num ;i++){
            switch(i){
                case 0:
                    fragmentList.add(FragmentAddLight_step1.newInstance());
                    break;
                case 1:
                    fragmentList.add(FragmentAddLight_step2.newInstance());
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
        getMenuInflater().inflate(R.menu.menu_add__light, menu);
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

    public Appliance getAppliance(){
        return this.appliance;
    }

    public void saveAppliance(){

        MainController main = ObjectReader.loadMainController(appliance.getMainControllerID());
        main.addAppliance(appliance);
        ObjectWriter.WriteAppliance(main,appliance.getMainControllerID());

        Intent intent = new Intent(Add_LightActivity.this, ApplianceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
