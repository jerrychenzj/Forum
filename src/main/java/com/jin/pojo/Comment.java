package com.jin.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by JINS on 2018/2/27.
 */
public class Comment implements Serializable{
    private int    id;
    private String content;
    private int    user_id;
    private Date   created_date;
    private int    entity_id;
    private int    entity_type;
    private int    status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public int getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(int entity_type) {
        this.entity_type = entity_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Comment(){}

    public Comment(int id, String content, int user_id, Date created_date, int entity_id, int entity_type, int status) {
        this.id = id;
        this.content = content;
        this.user_id = user_id;
        this.created_date = created_date;
        this.entity_id = entity_id;
        this.entity_type = entity_type;
        this.status = status;
    }
}
