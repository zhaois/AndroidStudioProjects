package itisme.com.cn.card;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setFullScreen();
        setContentView(R.layout.activity_main);
    }

    public void writeRule(View view){
        // 跳转到规则写入界面
        Intent intent = new Intent(MainActivity.this, WriteRuleAty.class);
        startActivity(intent);
    }
    private void  setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);  //设置全屏

    }
    public void lookRuleList(View view){
        Intent intent = new Intent(MainActivity.this,LookUpRulesAty.class);
        startActivity(intent);
        //跳转到规则查看界面
    }

    public void syncCloud(View view){
        //同步数据到云端
    }
}
