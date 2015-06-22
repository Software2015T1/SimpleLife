package com.example.user.simplelife;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.util.Date;

/**
 * Created by bostenkg5 on 2015/6/22.
 */
public class GPSService extends Service{
    private static boolean isThreadRunning = false;

    private Handler handler = new Handler();
    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isThreadRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.postDelayed(showTime, 1000);
        return START_NOT_STICKY;
    }
    public void onDestroy(){
        super.onDestroy();
        isThreadRunning = false;
    }

    private Runnable showTime = new Runnable() {
        public void run() {
            Log.v("fuck", new Date().toString());
            handler.postDelayed(this, 1000);
        }
    };

    public static boolean getIsThreadRunning(){
        return isThreadRunning;
    }

}
