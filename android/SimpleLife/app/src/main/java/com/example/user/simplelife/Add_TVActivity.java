package com.example.user.simplelife;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import java.util.ArrayList;


public class Add_TVActivity extends Add_Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__tv);

        fragmentList = new ArrayList<Fragment>();
        index = 0;
        frame_num = 4;
        fragment_id = R.id.addTV_layout;
        for(int i = 0 ; i< frame_num ;i++){
            switch(i){
                case 0:
                    fragmentList.add(FragmentAddTV_step1.newInstance());
                    break;
                case 1:
                    fragmentList.add(FragmentAddTV_step2.newInstance());
                    break;
                case 2:
                    fragmentList.add(FragmentAddTV_step3.newInstance());
                    break;
                case 3:
                    fragmentList.add(FragmentAddTV_step4.newInstance());
                    break;
            }
        }
        changeFragment(fragmentList.get(index));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add__tv, menu);
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