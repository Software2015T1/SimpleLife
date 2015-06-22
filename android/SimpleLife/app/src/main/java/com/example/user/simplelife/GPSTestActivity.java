package com.example.user.simplelife;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;


public class GPSTestActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnCCC ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpstest);
        btnCCC =(Button)findViewById(R.id.btnCCC);
        btnCCC.setOnClickListener(this);
        btnCCC.setText("台大");

    }

    @Override
    public void onClick(View v) {
        Geocoder gc = new Geocoder(this, Locale.TRADITIONAL_CHINESE);
        try {
            List<Address> results = gc.getFromLocation(GPSService.Latitude, GPSService.Longitude, 5);
            Address address = results.get(0);
            results = gc.getFromLocationName("桃園縣龜山鄉民生北路1段292巷15號",5);
            Address dst = results.get(0);
            TextView textviewDst =(TextView)findViewById(R.id.textViewDst);
            TextView textview = (TextView)findViewById(R.id.textView);
            textview.setText(address.getAddressLine(0));
            textviewDst.setText(dst.getAddressLine(0));
            TextView textViewDistance = (TextView)findViewById(R.id.textViewDistance);
            float[] dis= new float[10];
            Location.distanceBetween(address.getLatitude(),address.getLongitude(),dst.getLatitude(),dst.getLongitude(),dis);
            textViewDistance.setText(Float.toString(dis[0]));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
