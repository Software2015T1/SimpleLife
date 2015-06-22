package com.example.user.simplelife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddmain_step5.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddmain_step5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddmain_step5 extends FragmentAdd_step {

    public static FragmentAddmain_step5 newInstance() {
        FragmentAddmain_step5 fragment = new FragmentAddmain_step5();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddmain_step5() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addmain_step5, container, false);
        Button nextButton = (Button) view.findViewById(R.id.btnDone_addmain);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onFragmentInteraction("next");
            }
        });
        return view;
    }
}
