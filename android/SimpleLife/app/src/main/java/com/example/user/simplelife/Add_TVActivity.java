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


public class Add_TVActivity extends FragmentActivity
        implements FragmentAddTV_step1.OnFragmentInteractionListener, FragmentAddTV_step2.OnFragmentInteractionListener,
        FragmentAddTV_step3.OnFragmentInteractionListener, FragmentAddTV_step4.OnFragmentInteractionListener{

    private float x1;
    private float x2;
    private float y1;
    private float y2;
    private int index;
    private int frame_num;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__tv);

        fragmentList = new ArrayList<Fragment>();
        index = 0;
        frame_num = 4;
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

    private void changeFragment(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.addTV_layout, f);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(String arg) {
        if(arg.equals("next")){
            if(this.index!=frame_num-1) {
                this.index++;
                changeFragment(fragmentList.get(this.index));
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            x2 = event.getX();
            y2 = event.getY();
            if(x1 - x2 > 50) {
                if(this.index != 0){
                    this.index--;
                    changeFragment(fragmentList.get(this.index));
                }
            } else if(x2 - x1 > 50) {
                if(this.index != frame_num-1){
                    this.index++;
                    changeFragment(fragmentList.get(this.index));
                }
            }
        }
        return super.onTouchEvent(event);
    }
}