package cn.com.itisme.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by zhao on 5/11/15.
 */
public class BindCountService extends Service{
    private int count;
    private boolean threadDisable = false;
    private String activityValue = null;
    private String TAG = "BindCountService";
    private ServiceBinder serviceBinder = new ServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"on bind");
        Bundle bundle = intent.getExtras();
        activityValue = bundle.getString(getString(R.string.activity_to_bind_service));
        Log.i(TAG, activityValue);


        return serviceBinder;
    }
    public class ServiceBinder extends Binder implements ICountService{

        @Override
        public int getCount() {
            return count;
        }
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
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    Log.i(TAG, "Count is "+count);
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
        Log.i(TAG,"on start");

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
        Log.i(TAG,"on destroy");
    }
}
