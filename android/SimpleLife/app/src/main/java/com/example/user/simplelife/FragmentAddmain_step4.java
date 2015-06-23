package com.example.user.simplelife;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddmain_step4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddmain_step4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddmain_step4 extends FragmentAdd_step {

    EditText editTextHomeAddress;
    public static FragmentAddmain_step4 newInstance() {
        FragmentAddmain_step4 fragment = new FragmentAddmain_step4();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddmain_step4() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_addmain_step4, container, false);
        editTextHomeAddress = (EditText)view.findViewById(R.id.editTextLocation_addmain);
        String homeAddress="";
        try {

            homeAddress = GeoCalculator.getNowAddress(getActivity());

        } catch (Exception e) {
            e.printStackTrace();
        }

        editTextHomeAddress.setText(homeAddress);
        ImageButton nextButton = (ImageButton) view.findViewById(R.id.ibtnNext_addmain4);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String home = editTextHomeAddress.getText().toString();
                UserProfile.homeAddress = home;

                MainController main = (MainController)((Add_MainControllerActivity)getActivity()).getMainController();
                EditText location = (EditText)view.findViewById(R.id.editTextLocation_addmain);
                main.setAddress(location.getText().toString());

                mListener.onFragmentInteraction("next");
            }
        });
        return view;
    }
}
