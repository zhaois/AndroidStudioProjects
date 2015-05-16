package cn.com.itisme.bluemusic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//学习本实例之前，请掌握Activity的生命周期和相关的方法，这样学习效果会更好。
public class MainActivity extends Activity {
    private MediaPlayer mediaplayer;
    private EditText txtName;
    private int postion;
    private String fileName;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaplayer =MediaPlayer.create(MainActivity.this,R.raw.hello);
        ButtonClickListener listener =new ButtonClickListener();
        txtName =(EditText)this.findViewById(R.id.inputName);
        Button btnPlay =(Button)this.findViewById(R.id.btnPlay);
        Button btnPause =(Button) this.findViewById(R.id.btnPause);
        Button btnStop =(Button) this.findViewById(R.id.btnStop);
        Button btnResart=(Button) this.findViewById(R.id.btnRestart);
        btnPlay.setOnClickListener(listener);
        btnPause.setOnClickListener(listener);
        btnStop.setOnClickListener(listener);
        btnResart.setOnClickListener(listener);
    }
    //当系统恢复后，可以重新读取出之前保存的状态值
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        this.fileName=savedInstanceState.getString("fileName");
        this.postion=savedInstanceState.getInt("postion");
        super.onRestoreInstanceState(savedInstanceState);
    }
    //当发生意外时，在系统将Activity的进程杀死之前，保存一些状态值
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("fileName", fileName);
        outState.putInt("postion",postion);
        super.onSaveInstanceState(outState);
    }
    //onDestroy方法可以杀掉程序的进程,彻底释放资源
    @Override
    protected void onDestroy() {
        mediaplayer.release();
        super.onDestroy();
    }
    //如果打电话结束了，继续播放音乐
    @Override
    protected void onResume() {
        if(postion>0&&fileName!=null)
        {
            try {
                play();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mediaplayer.seekTo(postion);
            postion=0;
        }
        super.onResume();
    }
    //如果突然来电话或者来短信，Acticity会暂停，停止播放音乐
    @Override
    protected void onPause() {
        if(mediaplayer.isPlaying())
        {
            postion =mediaplayer.getCurrentPosition();//保存当前播放点
            mediaplayer.stop();
        }

        super.onPause();
    }

    private final class ButtonClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub


            Button button =(Button) v ;
            try {
                switch (v.getId())
                {
                    //播放
                    case R.id.btnPlay:
                        if(!mediaplayer.isPlaying())
                        {
                            play();
                        }
                        break;
                    //暂停
                    case R.id.btnPause:
                        //如果正在播放，则按下按钮后暂停.且按钮上的文本显示为"继续“
                        if(mediaplayer.isPlaying())
                        {
                            mediaplayer.pause();
                            button.setText(R.string.txtContinue);//设置按钮文本
                        }else{
                            //如果是暂停状态，按下按钮后继续播放
                            //play();
                        }
                        break;
                    //停止
                    case R.id.btnStop:
                        if(mediaplayer.isPlaying()){
                            mediaplayer.stop();
                        }
                        break;

                    //重复
                    case R.id.btnRestart:
                        if(mediaplayer.isPlaying()){
                            mediaplayer.seekTo(0);
                        }else{
                            play();
                        }
                        break;
                }
            }catch (Exception e) {
                // TODO: handle exception
            }


        }

    }
    private void play() throws IOException
    {

        //mediaplayer.prepare();
        mediaplayer.start();
    }
}
