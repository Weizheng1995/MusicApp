package com.yx.model;

/**
 * Created  on 2017/5/30.
 * 音乐model
 */

public class Music {
    // 编号
    private int id;
    // 名称
    private String name;
    // 播放次数
    private int count;

    public Music() {
    }

    public Music(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        sb.append(id+",");
        sb.append(name+",");
        sb.append(count+"\n");
        return sb.toString();
    }
}
