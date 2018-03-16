package com.jin.service.impl;

import com.jin.service.FollowService;
import com.jin.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by JINS on 2018/3/7.
 */
@Service
public class FollowServiceImpl implements FollowService{

    @Autowired
    private JedisPool jedisPool;

    @Override
    public boolean follow(int userId, int entityId, int entityType) {
        Jedis jedis = jedisPool.getResource();
        Date date = new Date();
        String followerKey = RedisKeyUtils.getFollowerKey(entityType,entityId);
        String followeeKey = RedisKeyUtils.getFolloweeKey(userId,entityType);
        Transaction tx = jedis.multi();
        tx.zadd(followerKey,date.getTime(),String.valueOf(userId));
        tx.zadd(followeeKey,date.getTime(),String.valueOf(entityId));
        List<Object> ret = tx.exec();
        if (jedis!=null) jedis.close();
        return ret.size()==2&&(Long)ret.get(0)>0&&(Long)ret.get(1)>0;
    }

    @Override
    public boolean unfollow(int userId, int entityId, int entityType) {
        Jedis jedis = jedisPool.getResource();
        String followerKey = RedisKeyUtils.getFollowerKey(entityType,entityId);
        String followeeKey = RedisKeyUtils.getFolloweeKey(userId,entityType);
        Transaction tx = jedis.multi();
        tx.zrem(followerKey,String.valueOf(userId));
        tx.zrem(followeeKey,String.valueOf(entityId));
        List<Object> ret = tx.exec();
        if (jedis!=null) jedis.close();
        return ret.size()==2&&(Long)ret.get(0)>0&&(Long)ret.get(1)>0;
    }

    private List<Integer> getIdsFromSet(Set<String> set){
        List<Integer> list = new ArrayList<>();
        for(String s:set){
            list.add(Integer.valueOf(s));
        }
        return list;
    }

    @Override
    public List<Integer> getFollowers(int entityId, int entityType, int count) {
        Jedis jedis = jedisPool.getResource();
        String followerKey = RedisKeyUtils.getFollowerKey(entityType,entityId);
        List<Integer> list = getIdsFromSet(jedis.zrevrange(followerKey,0,count));
        if (jedis!=null) jedis.close();
        return list;
    }

    @Override
    public List<Integer> getFollowers(int entityId, int entityType, int offset, int count) {
        Jedis jedis = jedisPool.getResource();
        String followerKey = RedisKeyUtils.getFollowerKey(entityType,entityId);
        List<Integer> list = getIdsFromSet(jedis.zrevrange(followerKey,offset,count));
        if (jedis!=null) jedis.close();
        return list;
    }

    @Override
    public List<Integer> getFollowees(int userId, int entityType, int count) {
        Jedis jedis = jedisPool.getResource();
        String followeeKey = RedisKeyUtils.getFolloweeKey(userId,entityType);
        List<Integer> list = getIdsFromSet(jedis.zrevrange(followeeKey,0,count));
        if (jedis!=null) jedis.close();
        return list;
    }

    @Override
    public List<Integer> getFollowees(int userId, int entityType, int offset, int count) {
        Jedis jedis = jedisPool.getResource();
        String followeeKey = RedisKeyUtils.getFolloweeKey(userId,entityType);
        List<Integer> list = getIdsFromSet(jedis.zrevrange(followeeKey,offset,count));
        if (jedis!=null) jedis.close();
        return list;
    }

    @Override
    public long getFollowerCount(int entityId, int entityType) {
        Jedis jedis = jedisPool.getResource();
        String followerKey = RedisKeyUtils.getFollowerKey(entityType,entityId);
        long count = jedis.zcard(followerKey);
        if (jedis!=null) jedis.close();
        return count;
    }

    @Override
    public long getFolloweeCount(int userId, int entityType) {
        Jedis jedis = jedisPool.getResource();
        String followeeKey = RedisKeyUtils.getFolloweeKey(userId,entityType);
        long count = jedis.zcard(followeeKey);
        if (jedis!=null) jedis.close();
        return count;
    }

    @Override
    public boolean isFolloer(int userId, int entityId, int entityType) {
        Jedis jedis = jedisPool.getResource();
        String followerKey = RedisKeyUtils.getFollowerKey(entityType,entityId);
        boolean flag = jedis.zscore(followerKey,String.valueOf(userId)) != null;
        if (jedis!=null) jedis.close();
        return flag;
    }
}
