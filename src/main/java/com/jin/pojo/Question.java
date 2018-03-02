package com.jin.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by JINS on 2018/2/27.
 */
public class Question implements Serializable{
    private int id;
    private String title;
    private String content;
    private int    user_id;
    private Date created_date;
    private int comment_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public Question(){}

    public Question(int id, String title, String content, int user_id, Date created_date, int comment_count) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user_id = user_id;
        this.created_date = created_date;
        this.comment_count = comment_count;
    }
}
