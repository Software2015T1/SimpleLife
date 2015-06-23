package com.example.user.simplelife;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddOther_step3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddOther_step3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddOther_step3 extends FragmentAdd_step {

    public static FragmentAddOther_step3 newInstance() {
        FragmentAddOther_step3 fragment = new FragmentAddOther_step3();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddOther_step3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_other_step3, container, false);
        Button nextButton = (Button) view.findViewById(R.id.btnDone_addOther);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Other appliance = (Other)((Add_OtherActivity)getActivity()).getAppliance();
                EditText name = (EditText)view.findViewById(R.id.editTextName_addOther);
                appliance.setName(name.getText().toString());
                mListener.onFragmentInteraction("done");
            }
        });
        return view;
    }
}
