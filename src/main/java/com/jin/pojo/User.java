package com.jin.pojo;

import java.io.Serializable;

/**
 * Created by JINS on 2018/2/27.
 */
public class User implements Serializable{
    private int    id;
    private String name;
    private String password;
    private String salt;
    private String head_url;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public User(){}

    public User(int id, String name, String password, String salt, String head_url) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.head_url = head_url;
    }
}
