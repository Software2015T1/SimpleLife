package com.example.user.simplelife;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;


public class GPSTestActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnCCC ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpstest);
        btnCCC =(Button)findViewById(R.id.btnCCC);
        btnCCC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Geocoder gc = new Geocoder(this);
        try {
            List<Address> results = gc.getFromLocation(GPSService.Latitude,GPSService.Longitude,5);
            Address address = results.get(0);
            TextView textview = (TextView)findViewById(R.id.textView);
            textview.setText(address.getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
