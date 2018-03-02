package com.jin.pojo;

import org.springframework.stereotype.Component;

/**
 * Created by JINS on 2018/2/28.
 */
@Component
public class HostHolder {

    private static ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }
}
