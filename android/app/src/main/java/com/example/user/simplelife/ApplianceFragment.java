package com.example.user.simplelife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 2015/6/14.
 */
public class ApplianceFragment extends Fragment {
    private String value = "";

    public void onAttach(Activity activity){
        super.onAttach(activity);

        ApplianceActivity mainActivity = (ApplianceActivity)activity;
        value = mainActivity.getHomeData();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_appliance, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //TextView txtResult = (TextView)this.getView().findViewById(R.id.textView1);
        //txtResult.setText(value);
    }
}