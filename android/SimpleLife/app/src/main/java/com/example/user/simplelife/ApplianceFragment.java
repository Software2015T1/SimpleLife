package com.example.user.simplelife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2015/6/14.
 */
public class ApplianceFragment extends Fragment {
    private GridView gridView;
    private ArrayList<MainController> mainControllers;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> items = new ArrayList<>();
    private ArrayList<Device> devices = new ArrayList<Device>();

    public void onAttach(Activity activity){
        super.onAttach(activity);

        ApplianceActivity mainActivity = (ApplianceActivity)activity;
    }
    public void onCreate(Bundle savedInstanceState) {
        setItems();
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_appliance, container, false);
        adapter = new SimpleAdapter(getActivity(),
                items, R.layout.grid_item, new String[]{"image", "text"},
                new int[]{R.id.image, R.id.text});

        gridView = (GridView)v.findViewById(R.id.gridView_appliance);
        gridView.setNumColumns(3);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == gridView.getCount() - 1) {
                    Intent intent = new Intent(getActivity(), AddProductActivity.class);
                    startActivity(intent);
                } else {
                    Device device = devices.get(position);
                    if (device.getType().equals("Main")) {
                        Intent intent = new Intent(getActivity(), MainControllerActivity.class);
                        intent.putExtra("device", device);
                        startActivity(intent);
                    } else if (device.getType().equals("Light")) {
                        Intent intent = new Intent(getActivity(), LightActivity.class);
                        intent.putExtra("device", device);
                        startActivity(intent);
                    } else if (device.getType().equals("AC")) {
                        Intent intent = new Intent(getActivity(), AirConditionerActivity.class);
                        intent.putExtra("device", device);
                        startActivity(intent);
                    } else if (device.getType().equals("TV")) {
                        Intent intent = new Intent(getActivity(), TVActivity.class);
                        intent.putExtra("device", device);
                        startActivity(intent);
                    } else if (device.getType().equals("Other")) {
                        Intent intent = new Intent(getActivity(), OtherActivity.class);
                        intent.putExtra("device", device);
                        startActivity(intent);
                    }
                }
            }


        });



        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //TextView txtResult = (TextView)this.getView().findViewById(R.id.textView1);
        //txtResult.setText(value);
    }

    public  ArrayList<MainController> getMainControllers(){
        ArrayList<MainController> mainControllers = new ArrayList<MainController>();
        if(new File("/sdcard/MC_ID").exists()) {
            ArrayList<String> mcName = ObjectReader.loadMC("MC_ID");
            for(int i=0; i<mcName.size();i++) {
                mainControllers.add(ObjectReader.loadMainController(mcName.get(i)));
            }
        }

        return mainControllers;
    }

    public void setItems(){
        mainControllers = getMainControllers();

        for (int i = 0; i < mainControllers.size(); i++) {
            MainController main = mainControllers.get(i);
            devices.add(main);
            Map<String, Object> main_item = new HashMap<>();
            main_item.put("image",main.getImage());
            main_item.put("text", main.getName());
            items.add(main_item);

            ArrayList<Appliance> appliances = main.getAppliances();
            for (int j = 0 ; j < appliances.size(); j++){
                Appliance appliance = appliances.get(j);
                devices.add(appliance);
                Map<String, Object> item = new HashMap<>();
                item.put("image",appliance.getImage());
                item.put("text", appliance.getName());
                items.add(item);
            }
        }
        Map<String, Object> add_item = new HashMap<>();
        add_item.put("image",R.drawable.plus_button);
        add_item.put("text", "Add Product");
        items.add(add_item);
    }

}