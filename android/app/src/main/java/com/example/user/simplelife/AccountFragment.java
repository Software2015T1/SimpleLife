package com.example.user.simplelife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by User on 2015/6/14.
 */
public class AccountFragment extends Fragment {
    private String value = "";
    private ListView listView;
    private View v;

    public void onAttach(Activity activity){
        super.onAttach(activity);

        ApplianceActivity mainActivity = (ApplianceActivity)activity;
        value = mainActivity.getSettingData();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.fragment_account, container, false);
        listView = (ListView)v.findViewById(R.id.listView_Account);
        final String[] arr = new String[]{ "Profile","Contact us" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(position == 0){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ProfileActivity.class);
                    getActivity().startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ContactUsActivity.class);
                    getActivity().startActivity(intent);
                }
            }

        });
        return v;
    }


    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }
}
