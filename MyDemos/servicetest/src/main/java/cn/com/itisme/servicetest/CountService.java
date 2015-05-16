package cn.com.itisme.servicetest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class CountService extends Service  {
    private boolean threadDisable = false;
    private String activityValue = null;
    private String TAG = "CountService";
    private int count;
    public CountService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "on bind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "on create");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!threadDisable){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }
                    count++;
                    Log.i(TAG,"Count is " + count);
                }
            }
        }).start();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG,"on rebind");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "on start");
        Bundle bundle = intent.getExtras();
        activityValue = bundle.getString(getString(R.string.activity_to_service));
        Log.i(TAG, activityValue);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "on unbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.threadDisable = true;
        Log.i(TAG, "on destroy");
    }

    public int getCount() {
        return count;
    }
}
