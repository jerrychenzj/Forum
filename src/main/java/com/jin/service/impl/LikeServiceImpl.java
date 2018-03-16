package com.jin.service.impl;

import com.jin.service.LikeService;
import com.jin.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by JINS on 2018/3/5.
 */
@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    JedisPool jedisPool;

    @Override
    public long like(int userid, int entitytype, int entityid) {
        Jedis jedis = jedisPool.getResource();
        String likekey = RedisKeyUtils.getLikeKey(entitytype,entityid);
        jedis.sadd(likekey,String.valueOf(userid));

        String dislikeKey = RedisKeyUtils.getDislikeKey(entitytype,entityid);
        jedis.srem(dislikeKey,String.valueOf(userid));

        long num = jedis.scard(likekey);
        jedis.close();
       return num;
    }

    @Override
    public long dislike(int userid, int entitytype, int entityid) {
        Jedis jedis = jedisPool.getResource();
        String likekey = RedisKeyUtils.getLikeKey(entitytype,entityid);
        jedis.srem(likekey,String.valueOf(userid));

        String dislikeKey = RedisKeyUtils.getDislikeKey(entitytype,entityid);
        jedis.sadd(dislikeKey,String.valueOf(userid));


         long num=jedis.scard(likekey);
         jedis.close();
         return num;
    }

    @Override
    public int getLikeStatus(int userid, int entitytype, int entityid) {
        Jedis jedis = jedisPool.getResource();
        int status = 0;
        if(jedis.sismember(RedisKeyUtils.getLikeKey(entitytype,entityid),String.valueOf(userid))){
            status = 1;
        }
        if(jedis.sismember(RedisKeyUtils.getDislikeKey(entitytype,entityid),String.valueOf(userid))){
            status = -1;
        }
        jedis.close();
        return status;
    }

    @Override
    public long getLikeCount(int entitytype, int entityid) {
        Jedis jedis = jedisPool.getResource();
        String likekey = RedisKeyUtils.getLikeKey(entitytype,entityid);
        long num = jedis.scard(likekey);
        jedis.close();
        return num;
    }
}
