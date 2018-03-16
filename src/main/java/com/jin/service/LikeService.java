package com.jin.service;

/**
 * Created by JINS on 2018/3/5.
 */
public interface LikeService {
    long like(int userid,int entitytype,int entityid);
    long dislike(int userid,int entitytype,int entityid);
    int getLikeStatus(int userid,int entitytype,int entityid);
    long getLikeCount(int entitytype,int entityid);
}
