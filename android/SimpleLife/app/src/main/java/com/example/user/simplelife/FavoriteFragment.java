package com.example.user.simplelife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2015/6/14.
 */
public class FavoriteFragment extends Fragment {
    private ListView list;
    private Favorite_ListAdapter adapter;
    private View view;


    public void onAttach(Activity activity){
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        list = (ListView) view.findViewById(R.id.listView_favorite);
        if(new File("sdcard/MC_ID").exists())
        adapter = new Favorite_ListAdapter(getActivity());
        ArrayList favorites = null;
        if(new File("sdcard/FAVORITE.fav").exists()) {
            favorites = (ArrayList<String>) ObjectReader.loadObject("FAVORITE.fav");
            adapter.setDeviceName(favorites);
        }
        if(adapter!=null)
        list.setAdapter(adapter);



        ImageButton btn = (ImageButton) view.findViewById(R.id.ibtnAddFavorite);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddFavoriteActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}