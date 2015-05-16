package cn.com.itisme.servicetest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;


public class MainActivity extends
        Activity {

    private static final String TAG ="ServiceDemo";
    private ICountService countService = null;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            countService = (ICountService) service;
            Log.i("CountService","on service connected ,count is" + countService.getCount());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            countService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Start a particular service
     * @param view
     */
    public void startService(View view){
        Log.i(TAG,"onClick : starting service");
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.activity_to_service),"i come from activity");
        intent.putExtras(bundle);
        intent.setClass(MainActivity.this, CountService.class);
        this.startService(intent);
    }

    /**
     * stop a particular service
     * @param view
     */

    public void stopService(View view){
        Log.i(TAG, "onClick :stopping service");
        this.stopService(new Intent(this, CountService.class));
    }

    /**
     * bind a service for the activity
     * @param view
     */
    public void bindService(View view){
        Log.i(TAG, "onClick :bind service");
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.activity_to_bind_service),"i come from activity");
        intent.putExtras(bundle);
        intent.setClass(this, BindCountService.class);
        this.bindService(intent, this.serviceConnection, BIND_AUTO_CREATE);
    }

    /**
     * unbind a service from the activity
     * @param view
     */
    public void unbindService(View view){
        Log.i(TAG, "onClick： unbind service");
        if(countService != null) {
            this.unbindService(serviceConnection);
        }
    }

    /**
     * count the number of service
     * @param view
     */
    public void countService(View view){
        if(countService!=null) {
            Log.i(TAG, "onClick : getCount=" + countService.getCount());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isServiceExisted(this,"BindCountService")) {
            this.unbindService(serviceConnection);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isServiceExisted(this,"BindCountService")) {
            this.unbindService(serviceConnection);
        }
        if(isServiceExisted(this,"CountService")){
            this.stopService(new Intent(this, CountService.class));
        }
    }

    public boolean isServiceExisted(Context context, String className){
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceInfoList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if(!(serviceInfoList.size()>0)){
            return false;
        }
        for (ActivityManager.RunningServiceInfo runningServiceInfo : serviceInfoList){
            ComponentName serviceName = runningServiceInfo.service;
            if(serviceName.getClassName().equals(className)){
                return true;
            }
        }
        return false;
    }
}
