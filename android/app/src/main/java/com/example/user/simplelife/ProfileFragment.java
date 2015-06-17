package com.example.user.simplelife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.simplelife.ApplianceActivity;
import com.example.user.simplelife.R;

/**
 * Created by User on 2015/6/16.
 */
public class ProfileFragment extends Fragment {

    public void onAttach(Activity activity){
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }


    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }
}