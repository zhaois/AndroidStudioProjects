package itisme.com.cn.serializeabletest;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import itisme.com.cn.serializeabletest.Test.MyTestClass;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void gotoNextAct(View view){
        ArrayList<MyTestClass> arrayList = new ArrayList<MyTestClass>();
        for(int i = 0; i < 10; i++){
            MyTestClass myTestClass = new MyTestClass();
            myTestClass.setUserName("user:"+i);
            myTestClass.setPassword("password:"+i);
            myTestClass.setAge("age:"+i);
            arrayList.add(myTestClass);
        }
        Intent intent = new Intent();
        intent.putExtra("key",arrayList);
        intent.setClass(MainActivity.this,ResultActivity.class);
        startActivity(intent);


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
}
