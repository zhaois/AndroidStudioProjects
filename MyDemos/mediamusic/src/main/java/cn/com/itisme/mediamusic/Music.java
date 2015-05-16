package cn.com.itisme.mediamusic;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class Music extends Activity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer netMediaPlayer;
    private MediaService mediaService;
    String url = "http://yinyueshiting.baidu.com/data2/music/64303540/139024841431284461128.mp3?xcode=0f2191fa812d1beb5b6bc9a2c06e1757531f0d13aa7ee983";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mediaPlayer = MediaPlayer.create(this, R.raw.hello);
        mediaService = new MediaService(url);
        Intent intent = new Intent();
        intent.setClass(this,MediaService.class);
        startService(intent);
        initNetMediaPlayer(url);
    }

    public void play(View view) {
        Button button = (Button) view;
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            button.setText(R.string.play);
        }else {
            button.setText(R.string.pause);
            mediaPlayer.start();
        }

    }

    private void  initNetMediaPlayer(String url){

        netMediaPlayer = new MediaPlayer();
        netMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            netMediaPlayer.setDataSource(url);
            netMediaPlayer.prepare(); // might take long! (for buffering, etc)
            Log.i("Net","OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void netPlay(View view) {
        Button button = (Button) view;
        if(netMediaPlayer.isPlaying()){
            netMediaPlayer.pause();
            button.setText(R.string.net_play);
        }else {
            button.setText(R.string.net_pause);
            netMediaPlayer.start();
        }

    }

    public void backPlay(View view){
        mediaService.onPrepared(netMediaPlayer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(netMediaPlayer!=null){
            netMediaPlayer.release();
            netMediaPlayer=null;
        }
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music, menu);
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
