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
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddAir_step1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddAir_step1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddAir_step1 extends FragmentAdd_step {

    ArrayList<String> ids;
    ArrayList<String> names;
    ArrayList<String> arduino;

    public static FragmentAddAir_step1 newInstance() {
        FragmentAddAir_step1 fragment = new FragmentAddAir_step1();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddAir_step1() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_air_step1, container, false);
        setSpinnerView();
        ImageButton nextButton = (ImageButton) view.findViewById(R.id.ibtnNext_addAir1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AirConditioner appliance = (AirConditioner)((Add_AirActivity)getActivity()).getAppliance();
                Spinner spinner = (Spinner)view.findViewById(R.id.spinner_whichMain_addAir);
                String mainName =spinner.getSelectedItem().toString();
                for(int i=0 ;i < names.size(); i++){
                    if(mainName.equals(names.get(i))){
                        appliance.setMainControllerID(ids.get(i));
                        appliance.setMainControllerName(names.get(i));
                    }
                }
                String arduinoID="";
                Spinner spinner2 = (Spinner)view.findViewById(R.id.spinner_addAir);
                Object selItem = spinner2.getSelectedItem();
                if(selItem!=null)
                {
                    arduinoID = selItem.toString();
                }
                EditText audinoText = (EditText)view.findViewById(R.id.editTextID_addAir);
                if(!audinoText.getText().toString().equals("")){
                    arduinoID = audinoText.getText().toString();
                }
                if(arduinoID.equals(""))
                {
                    Toast.makeText(getActivity(),"Arduino Id can't be null",Toast.LENGTH_LONG).show();
                    return;
                }
                appliance.setArduinoID(arduinoID);
                MainController mc = ObjectReader.loadMainController(appliance.getMainControllerID());
                Integer noAppliance = mc.getAppliances().size();
                String deviceID = noAppliance.toString();
                appliance.setDeviceID(deviceID);
                mListener.onFragmentInteraction("next");
            }
        });
        return view;
    }

    public boolean hasArduino(String id){
        for(int i=0 ; i<arduino.size();i++){
            if(id.equals(arduino.get(i))){
                return true;
            }
        }
        return false;
    }

    public void setSpinnerView(){
        ids = ObjectReader.loadMC("MC_ID");
        names = ObjectReader.loadMC("MC_Name");
        arduino = new ArrayList<String>();
        for(int i=0 ; i<ids.size();i++) {
            MainController main = ObjectReader.loadMainController(ids.get(i));
            ArrayList<Appliance> appliances = main.getAppliances();
            for(int j=0 ;j<appliances.size();j++){
                if(!appliances.get(j).getType().equals("Light")){
                    String id = appliances.get(j).getArduinoID();
                    if(hasArduino(id) == false){
                        arduino.add(id);
                    }
                }
            }
        }

        Spinner mainName = (Spinner)view.findViewById(R.id.spinner_whichMain_addAir);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,names);
        mainName.setAdapter(adapter);

        Spinner arduinoName = (Spinner)view.findViewById(R.id.spinner_addAir);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,arduino);
        arduinoName.setAdapter(adapter2);
    }
}
