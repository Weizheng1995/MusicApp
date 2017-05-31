package com.yx.IOUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/5/30.
 * 文件读取工具类
 */

public class FileUtil {
    // 存储的文件常量名
    private static final String FILENAME="MusicApp.txt";
    private String strFilePath;
    /*
  * 初始化文件数据
  * */
    public String initText(){
        StringBuffer sb=new StringBuffer();
        sb.append("1,曹操,1\n");
        sb.append("2,刚好遇见你,1\n");
        sb.append("3,喜欢你,1\n");
        sb.append("4,年轮,1\n");
        sb.append("5,泡沫,1\n");
        sb.append("6,primadona,1\n");
        sb.append("7,千年泪,1\n");
        sb.append("8,认真的雪,1\n");
        sb.append("9,socan,1\n");
        sb.append("10,追月,1\n");
        return sb.toString();
    }
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public  List<String> readFile(String filePath) {
         strFilePath=filePath;
        // 用于存储返回从文件读取的数据
        List<String> musicList=new ArrayList<String>();
        File file = new File(filePath,FILENAME);
        BufferedReader reader = null;
        if (!file.exists()){
            writeFile(filePath,initText());
        }
//        writeFile(filePath,initText());
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                musicList.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return musicList;
    }
    public  void writeFile(String filePath, String content) {
        // 判断目录
        File file=new File(filePath,"hello.txt");
        if(!file.exists()){
            file.mkdirs();
        }
        file=new File(filePath,FILENAME);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            content=initText();
        }
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(file.getPath(), false);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //更新数据
    public void updateFile(int index,String filePath){
        List<String> list=readFile(filePath);
        StringBuffer sb=new StringBuffer();
        String oldInfo=list.get(index-1);
        String array[]=oldInfo.split(",");
        int count=Integer.parseInt(array[2].charAt(0)+"");
        sb.append(array[0]+","+array[1]+","+(count+1));
        list.set(index-1,sb.toString());
        writeFile(filePath,listToString(list));
    }
    public String listToString(List<String> list){
        StringBuffer sb=new StringBuffer();
        int length=list.size();
        for(int i=0;i<length;i++){
            sb.append(list.get(i)+"\n");
        }
        sb.replace(length-2,length-1,"");
        return sb.toString();
    }
}
