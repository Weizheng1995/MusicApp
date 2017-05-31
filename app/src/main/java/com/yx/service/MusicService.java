package com.yx.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.yx.musicapp.R;

public class MusicService extends Service {
    private MediaPlayer mPlayer;// 定义音乐播放器
    private int music= R.raw.renzhendexue; //播放的音乐的名称

    public MusicService() {
    }

    public MusicService(int music) {
        this.music = music;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this,"MusicService create",Toast.LENGTH_SHORT).show();
        mPlayer=MediaPlayer.create(getApplicationContext(),music);
        // 设置重复播放
        mPlayer.setLooping(true);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mPlayer.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        mPlayer.start();
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mPlayer.stop();
        return super.onUnbind(intent);
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }
}
