package itisme.com.cn.card;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;



public class LoginAty extends Activity {
    private EditText etUserName;
    private EditText etPassword;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setFullScreen();
        setContentView(R.layout.activity_login_aty);

        etPassword = (EditText) findViewById(R.id.etPassword);
        etUserName = (EditText) findViewById(R.id.etUserName);

        preferences = getPreferences(Context.MODE_PRIVATE);
        etUserName.setText(preferences.getString(getString(R.string.user_name),""));
        etPassword.setText(preferences.getString(getString(R.string.passwrod),""));
    }
    private void  setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);  //设置全屏

    }

    private boolean isEmpty(String string){
        if(string == null || string.equals("")){
            return true;
        }else {
            return false;
        }
    }

    public void login(View view){
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if(isEmpty(userName)){
            toast(getString(R.string.empty_user_name));
            return;
        }
        if(isEmpty(password)){
            toast(getString(R.string.empty_password));
            return;
        }
        /**
         *  处理账户密码验证
          */
       //TODO：
        //......


        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getString(R.string.user_name),userName);
        editor.putString(getString(R.string.passwrod),password);
        editor.commit();
        Intent intent = new Intent(LoginAty.this,MainActivity.class);
        startActivity(intent);

    }

    private void toast(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

}
