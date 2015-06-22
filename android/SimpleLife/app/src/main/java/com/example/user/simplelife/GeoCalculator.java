package com.example.user.simplelife;

/**
 * Created by Smith on 2015/6/22.
 */
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoCalculator
{
    public static String getNowAddress(Activity activity)
    {
        Geocoder gc = new Geocoder(activity, Locale.TRADITIONAL_CHINESE);
        try {
            List<Address> results = gc.getFromLocation(GPSService.Latitude,GPSService.Longitude,5);
            if(results.size()>0)
            {
                Address address = results.get(0);
                return new String(address.getAddressLine(0));
            }
            else
            {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
