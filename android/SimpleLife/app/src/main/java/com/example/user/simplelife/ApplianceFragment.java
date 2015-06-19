package com.example.user.simplelife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2015/6/14.
 */
public class ApplianceFragment extends Fragment {
    private String value = "";
    private GridView gridView;
    private int[] image = {
            R.drawable.plus_button
    };
    private String[] imgText = {
            "Add product"
    };
    private SimpleAdapter adapter;
    private List<Map<String, Object>> items = new ArrayList<>();

    public void onAttach(Activity activity){
        super.onAttach(activity);

        ApplianceActivity mainActivity = (ApplianceActivity)activity;
        value = mainActivity.getHomeData();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_appliance, container, false);
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("image", image[i]);
            item.put("text", imgText[i]);
            items.add(item);
        }
        adapter = new SimpleAdapter(getActivity(),
                items, R.layout.grid_item, new String[]{"image", "text"},
                new int[]{R.id.image, R.id.text});

        gridView = (GridView)v.findViewById(R.id.gridView_appliance);
        gridView.setNumColumns(3);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == gridView.getCount() - 1)
                {
                    Intent intent = new Intent(getActivity(), AddProductActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(getActivity(), "you click "+imgText[position],
                        Toast.LENGTH_SHORT).show();
                Map<String, Object> item = new HashMap<>();
                item.put("image", R.drawable.plus_button);
                item.put("text",  "Add product");
                items.add(item);
                adapter.notifyDataSetChanged();
            }


        });
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //TextView txtResult = (TextView)this.getView().findViewById(R.id.textView1);
        //txtResult.setText(value);
    }
}