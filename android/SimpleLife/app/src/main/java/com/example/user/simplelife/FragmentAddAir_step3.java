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
 * {@link FragmentAddAir_step3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddAir_step3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddAir_step3 extends FragmentAdd_step {

    public static FragmentAddAir_step3 newInstance() {
        FragmentAddAir_step3 fragment = new FragmentAddAir_step3();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddAir_step3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_air_step3, container, false);
        ImageButton nextButton = (ImageButton) view.findViewById(R.id.ibtnNext_addAir3);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AirConditioner appliance = (AirConditioner)((Add_AirActivity)getActivity()).getAppliance();
                appliance.setBrand("None");
                mListener.onFragmentInteraction("next");
            }
        });
        return view;
    }
}
