package com.example.user.simplelife.AddMainController;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.simplelife.R;

/**
 * Created by User on 2015/6/18.
 */
public class Step1Fragment extends Fragment{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_addmain_step1, container, false);
    }
}
