package com.jin.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by JINS on 2018/2/28.
 */
public class LoginTicket implements Serializable{
    private int id;
    private int userid;
    private String ticket;
    private Date expired;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LoginTicket(){}

    public LoginTicket(int id, int userid, String ticket, Date expired, int status) {
        this.id = id;
        this.userid = userid;
        this.ticket = ticket;
        this.expired = expired;
        this.status = status;
    }
}
