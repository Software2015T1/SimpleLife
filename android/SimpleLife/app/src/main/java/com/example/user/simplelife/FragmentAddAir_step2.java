package com.example.user.simplelife;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAddmain_step1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAddmain_step1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddAir_step2 extends FragmentAdd_step {

    ArrayList<String> brands;

    public static FragmentAddAir_step2 newInstance() {
        FragmentAddAir_step2 fragment = new FragmentAddAir_step2();
        Bundle args = new Bundle();
        return fragment;
    }

    public FragmentAddAir_step2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_air_step2, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listView_addAir);
        brands = new ArrayList<String>();
        brands.add(getString(R.string.air_brand_1));
        brands.add(getString(R.string.air_brand_2));
        brands.add(getString(R.string.air_brand_3));
        ArrayAdapter listAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,brands);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AirConditioner appliance = (AirConditioner)((Add_AirActivity)getActivity()).getAppliance();
                appliance.setBrand(brands.get(position));
                mListener.onFragmentInteraction("next2");
            }
        });

        ImageButton nextButton = (ImageButton) view.findViewById(R.id.ibtnNext_addAir2);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onFragmentInteraction("next");
            }
        });
        return view;
    }
}
