package com.yx.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yx.IOUtil.FileUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView musicList;// 展示音乐的listView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 添加音乐列表
        addItem();
        //添加点击监听事件
        musicList=(ListView) findViewById(R.id.musicListView);
        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg=parent.getItemAtPosition(position).toString();
                // 跳转activity
                changeActivity(msg);
            }
        });
    }
    public void addItem(){
        FileUtil fileUtil=new FileUtil();
        musicList=(ListView) findViewById(R.id.musicListView);
        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,fileUtil.readFile(this.getFilesDir().getPath()));
        musicList.setAdapter(arrayAdapter);// 添加适配器
        arrayAdapter.notifyDataSetChanged();//刷新数据
    }
    public void changeActivity(String msg){
        String action="com.yx.playMusic";
        Intent intent=new Intent();
        intent.setAction(action);
        intent.putExtra("info",msg);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
            finish();// 关闭benactivity
        }
    }
}
