package com.example.user.simplelife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddLight_step2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddLight_step2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddLight_step2 extends FragmentAdd_step {

    View view;
    public static FragmentAddLight_step2 newInstance() {
        FragmentAddLight_step2 fragment = new FragmentAddLight_step2();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddLight_step2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_light_step2, container, false);
        Button nextButton = (Button) view.findViewById(R.id.btnDone_addLight);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Light appliance = (Light)((Add_LightActivity)getActivity()).getAppliance();
                EditText name = (EditText)view.findViewById(R.id.editTextName_addLight);
                appliance.setName(name.getText().toString());
                mListener.onFragmentInteraction("done");
            }
        });
        return view;
    }
}
