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
 * {@link FragmentAddLight_step1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddLight_step1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddLight_step1 extends FragmentAdd_step {

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
        View view = inflater.inflate(R.layout.fragment_add_light_step1, container, false);
        ImageButton nextButton = (ImageButton) view.findViewById(R.id.ibtnNext_addLight1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onFragmentInteraction("next");
            }
        });
        return view;
    }
}