package com.example.user.simplelife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddTV_step3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddTV_step3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddTV_step3 extends FragmentAdd_step {

    public static FragmentAddTV_step3 newInstance() {
        FragmentAddTV_step3 fragment = new FragmentAddTV_step3();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddTV_step3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_tv_step3, container, false);
        ImageButton nextButton = (ImageButton) view.findViewById(R.id.ibtnNext_addTV3);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TV appliance = (TV)((Add_TVActivity)getActivity()).getAppliance();
                appliance.setBrand("None");
                mListener.onFragmentInteraction("next");
            }
        });
        return view;
    }
}
