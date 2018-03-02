package com.jin.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by JINS on 2018/2/27.
 */
public class Message implements Serializable{
    private int id;
    private int fromid;
    private int toid;
    private String content;
    private int conversation_id;
    private Date created_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(int conversation_id) {
        this.conversation_id = conversation_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Message(){}

    public Message(int id, int fromid, int toid, String content, int conversation_id, Date created_date) {
        this.id = id;
        this.fromid = fromid;
        this.toid = toid;
        this.content = content;
        this.conversation_id = conversation_id;
        this.created_date = created_date;
    }
}
