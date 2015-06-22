package com.example.user.simplelife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddLight_step1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddLight_step1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddLight_step1 extends FragmentAdd_step {

    ArrayList<String> ids;
    ArrayList<String> names;

    public static FragmentAddLight_step1 newInstance() {
        FragmentAddLight_step1 fragment = new FragmentAddLight_step1();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddLight_step1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_light_step1, container, false);
        setSpinnerView();
        ImageButton nextButton = (ImageButton) view.findViewById(R.id.ibtnNext_addLight1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onFragmentInteraction("next");

                Light appliance = (Light)((Add_LightActivity)getActivity()).getAppliance();
                EditText audinoID = (EditText)view.findViewById(R.id.editText_ArduinoID_addlight);
                appliance.setDeviceID(audinoID.getText().toString());
                EditText motionID = (EditText)view.findViewById(R.id.editText_motionID_addlight);
                appliance.setMotionID(motionID.getText().toString());
                Spinner spinner = (Spinner)view.findViewById(R.id.spinner_whichMain_addLight);
                String mainName =spinner.getSelectedItem().toString();
                for(int i=0 ;i < names.size(); i++){
                    if(mainName.equals(names.get(i))){
                        appliance.setMainControllerID(ids.get(i));
                    }
                }

            }
        });
        return view;
    }

    public void setSpinnerView(){
        ids = ObjectReader.loadMC("MC_ID");
        names = ObjectReader.loadMC("MC_Name");

        Spinner mainName = (Spinner)view.findViewById(R.id.spinner_whichMain_addLight);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,names);
        mainName.setAdapter(adapter);
    }
}
