package com.example.user.simplelife;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.util.Date;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class GPSService extends Service{
    public static Double Longitude;
    public static Double Latitude;
    private static boolean isThreadRunning = false;
    private LocationManager manager;
    private Handler handler = new Handler();
    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {

        }
        isThreadRunning = true;
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        handler.postDelayed(showTime, 1000);
        return START_NOT_STICKY;
    }
    public void onDestroy(){
        isThreadRunning = false;
        super.onDestroy();
    }

    private Runnable showTime = new Runnable() {
        public void run() {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                Latitude = location.getLatitude();
                Longitude = location.getLongitude();
                Log.v("t", Latitude.toString());
                Log.v("t", Longitude.toString());

            }
            //Log.v("fuck", new Date().toString());
            handler.postDelayed(this, 1000);
        }


    };

    public static boolean getIsThreadRunning(){
        return isThreadRunning;
    }

}
