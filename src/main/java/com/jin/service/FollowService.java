package com.jin.service;

import java.util.List;

/**
 * Created by JINS on 2018/3/7.
 */
public interface FollowService {
    public boolean follow(int userId,int entityId,int entityType);
    public boolean unfollow(int userId,int entityId,int entityType);
    public List<Integer>  getFollowers(int entityId, int entityType, int count);
    public List<Integer>  getFollowers(int entityId,int entityType,int offset,int count);
    public List<Integer>  getFollowees(int userId,int entityType,int count);
    public List<Integer>  getFollowees(int userId,int entityType,int offset,int count);
    public long getFollowerCount(int entityId,int entityType);
    public long getFolloweeCount(int userId,int entityType);
    public boolean isFolloer(int userId,int entityId,int entityType);
}
