package cn.com.itisme.mediamusic;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class MediaService extends Service implements MediaPlayer.OnPreparedListener{
    private static  final String ACTION_PLAY = "cn.com.itisme.mediamusic.MediaService";
    private String url;
    private MediaPlayer mMediaPlayer = null;
    public MediaService(String url) {
        this.url = url;
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(ACTION_PLAY)) {
            mMediaPlayer = new MediaPlayer(); // initialize it here
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mMediaPlayer.setDataSource(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.prepareAsync(); // prepare async to not block main thread
        }
        return flags;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if(mp != null){
            mp.start();
        }
        //mp.start();

    }
}
