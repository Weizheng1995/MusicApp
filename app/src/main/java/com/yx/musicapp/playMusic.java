package com.yx.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yx.IOUtil.FileUtil;
import com.yx.service.MusicService;

public class playMusic extends AppCompatActivity {
    private String msg;// 存储相关的文件信息
    private TextView musicView;// 显示文件信息
    private String array[];// 存储分割后的数组
    private MediaPlayer mediaPlayer;// 音乐播放插件
    private Button startBtn;
    private Button stopBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        // 获取传入的参数
        Bundle bundle=getIntent().getExtras();
        msg=bundle.getString("info");
        setInfo();
        // 初始化播放器
        mediaPlayer=MediaPlayer.create(getApplicationContext(),getMusic());
        // 获取按钮
        startBtn=(Button) findViewById(R.id.startBtn);
        stopBtn=(Button)findViewById(R.id.stopBtn);
        //关闭停止播放按钮
        stopBtn.setEnabled(false);
    }
    // 设置相关信息
    public void setInfo(){
        musicView=(TextView)findViewById( R.id.musictextView);
        musicView.setText(msg);
    }
    // 监听事件
    public void onclick(View view){
        switch (view.getId()){
            case R.id.startBtn:
                //  播放
                startMusic();
                break;
            case R.id.stopBtn:
                // 停止
                stopMusic();
                break;
            case R.id.backBtn:
                // 返回音乐列表
                backToMain();
                break;
        }
    }
    public void startMusic(){
        FileUtil fileUtil=new FileUtil();
//        Intent intent=new Intent(playMusic.this,(new MusicService(getMusic())).getClass());
//        startService(intent);
//        Log.i("musicService","startService");
         mediaPlayer.start();
        startBtn.setEnabled(false);
        stopBtn.setEnabled(true);
        // 更新，播放次数加一
        fileUtil.updateFile(Integer.parseInt(array[0]),this.getFilesDir().getPath());
    }
    public void stopMusic(){
//        Intent intent=new Intent(playMusic.this, MusicService.class);
//        stopService(intent);
//        Log.i("musicService","stopMusic");
        mediaPlayer.stop();
        stopBtn.setEnabled(false);
        startBtn.setEnabled(true);
    }
    public void backToMain(){
        Intent intent=new Intent(playMusic.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 根据传过来的数据，播放对应的音乐
     * @return
     */
    public int getMusic(){
        int musicId;
        array=msg.split(",");
        int id=Integer.parseInt(array[0]);
        Log.i("getMusicID",id+"");
        // 选择
        switch(id){
            case 1:
                musicId=R.raw.caocao;
                break;
            case 2:
                musicId=R.raw.ganghaoyujianni;
                break;
            case 3:
                musicId=R.raw.iloveyou;
                break;
            case 4:
                musicId=R.raw.nianlun;
                break;
            case 5:
                musicId=R.raw.paomo;
                break;
            case 6:
                musicId=R.raw.primadona;
                break;
            case 7:
                musicId=R.raw.qiannianlei;
                break;
            case 8:
                musicId=R.raw.renzhendexue;
                break;
            case 9:
                musicId=R.raw.socan;
                break;
            case 10:
                musicId=R.raw.zhuiyue;
                break;
            default:
                musicId=R.raw.caocao;
                break;
        }
        return musicId;
    }
}
