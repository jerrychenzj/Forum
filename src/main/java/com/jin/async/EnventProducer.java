package com.jin.async;

import com.alibaba.fastjson.JSONObject;
import com.jin.utils.RedisKeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by JINS on 2018/3/6.
 */
@Service
public class EnventProducer {
        @Autowired
        JedisPool jedisPool;

    private static final Logger logger = LoggerFactory.getLogger(EnventProducer.class);

        public boolean fireEvent(EnventModel enventModel){
            try{
                Jedis jedis = jedisPool.getResource();
                 String json = JSONObject.toJSONString(enventModel);
                 String key = RedisKeyUtils.getEnventQueueKey();
                 jedis.lpush(key,json);
                 jedis.close();
                 return true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                logger.error("EnventProducer 出错"+e.getMessage());

            }
            return false;
        }
}
