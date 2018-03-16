package com.jin.utils;

import redis.clients.jedis.Jedis;

/**
 * Created by JINS on 2018/3/5.
 */
public class RedisKeyUtils {
    private static final String SPLIT = ":";
    private static final String BIZ_LIKE = "LIKE";
    private static final String BIZ_DISLIKE = "DISLIKE";
    private static final String BIZ_ENVENTQUEUE = "ENVENTQUEUE";
    private static final String BIZ_TIMELINE = "TIMELINE";

    //粉丝
    private static final String BIZ_FOLLOWER = "FOLLOWER";

    //关注对象
    private static final String BIZ_FOLLOWEE = "FOLLOWEE";



    public static  String getLikeKey(int entitytype,int entityid){
         return BIZ_LIKE+SPLIT+String.valueOf(entitytype)+SPLIT+String.valueOf(entityid);
    }

    public static  String getDislikeKey(int entitytype,int entityid){
        return BIZ_DISLIKE+SPLIT+String.valueOf(entitytype)+SPLIT+String.valueOf(entityid);
    }
    public static String getEnventQueueKey(){
        return BIZ_ENVENTQUEUE;
    }

    public static String getFollowerKey(int entitytype,int entityid){
        return BIZ_FOLLOWER+SPLIT+String.valueOf(entitytype)+SPLIT+String.valueOf(entityid);
    }
    public  static  String getFolloweeKey(int userId,int entitytype){
        return BIZ_FOLLOWEE+SPLIT+String.valueOf(userId)+SPLIT+String.valueOf(entitytype);
    }
    public  static  String getTimelineKey(int userId){
        return BIZ_TIMELINE + SPLIT + String.valueOf(userId);
    }
}
