package com.example.user.simplelife;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddmain_step1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddmain_step1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddmain_step2 extends FragmentAdd_step {

    public static FragmentAddmain_step2 newInstance() {
        FragmentAddmain_step2 fragment = new FragmentAddmain_step2();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddmain_step2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addmain_step2, container, false);
        ImageButton nextButton = (ImageButton) view.findViewById(R.id.ibtnNext_addmain2);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onFragmentInteraction("next");
            }
        });
        return view;
    }
}
