package com.jin.pojo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JINS on 2018/3/10.
 */
public class Feed implements Serializable{
    private int id;
    private int user_id;
    private int type;
    private Date created_date;
    //JSON
    private String data;

    private Map<String,Object> info;

    public void setData(String data) {
        this.data = data;
        JSONObject object = JSONObject.parseObject(data);
        info = new HashMap<>();
        for(Map.Entry<String,Object> e:object.entrySet()){
            info.put(e.getKey(),e.getValue());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getData() {
        return data;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public Feed(){}

    public Feed(int id, int user_id, int type, Date created_date, String data) {
        this.id = id;
        this.user_id = user_id;
        this.type = type;
        this.created_date = created_date;
        this.data = data;
    }
}
