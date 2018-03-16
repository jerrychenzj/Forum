package com.jin.async;

/**
 * Created by JINS on 2018/3/6.
 */
public enum  EnventType {
    LIKE(0),
    COMMENT(1),
    LOGINERRO(2),
    FOLLOWER(3),
    UNFOLLOWER(4),
    ADDINDEX(5);

    private int value;
    EnventType(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
