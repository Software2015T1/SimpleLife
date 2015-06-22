package com.example.user.simplelife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;


public abstract class Add_Activity extends FragmentActivity
        implements FragmentAdd_step.OnFragmentInteractionListener{
    private float x1;
    private float x2;
    private float y1;
    private float y2;
    protected int index;
    protected int frame_num;
    protected ArrayList<Fragment> fragmentList;
    protected int fragment_id;

    protected void changeFragment(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(fragment_id, f);
        transaction.commitAllowingStateLoss();
    }

    protected void setBackButtonListener(ImageButton button){
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Add_Activity.this, AddProductActivity.class);
                startActivity(intent);;
            }
        });
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
            if(x2 - x1 > 50) {

                if(this.index != 0){
                    this.index--;
                    changeFragment(fragmentList.get(this.index));
                }
            } else if(x1 - x2 > 50) {
                if(this.index != frame_num-1){
                    this.index++;
                    changeFragment(fragmentList.get(this.index));
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
