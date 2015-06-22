package com.example.user.simplelife;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Add_MainControllerActivity extends Add_Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main_contoller);
        fragmentList = new ArrayList<Fragment>();
        index = 0;
        frame_num = 5;
        fragment_id = R.id.addMain_layout;
        for(int i = 0 ; i< frame_num ;i++){
            switch(i){
                case 0:
                    fragmentList.add(FragmentAddmain_step1.newInstance());
                    break;
                case 1:
                    fragmentList.add(FragmentAddmain_step2.newInstance());
                    break;
                case 2:
                    fragmentList.add(FragmentAddmain_step3.newInstance());
                    break;
                case 3:
                    fragmentList.add(FragmentAddmain_step4.newInstance());
                    break;
                case 4:
                    fragmentList.add(FragmentAddmain_step5.newInstance());
                    break;
            }
        }
        changeFragment(fragmentList.get(index));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_main_contoller, menu);
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
