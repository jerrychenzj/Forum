package com.jin.async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin.utils.RedisKeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JINS on 2018/3/6.
 */
@Service
public class EnventConsumer implements InitializingBean,ApplicationContextAware{
     private Map<EnventType,List<EnventHandler>> config = new HashMap<>();
     private ApplicationContext applicationContext;

     @Autowired
     JedisPool jedisPool;

    private static final Logger logger = LoggerFactory.getLogger(EnventConsumer.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,EnventHandler> beans = applicationContext.getBeansOfType(EnventHandler.class);
        if(beans != null){
            for(Map.Entry<String,EnventHandler> entry:beans.entrySet()){
                List<EnventType>  enventTypes= entry.getValue().getSupportEnventTypes();
                for(EnventType enventType:enventTypes){
                    if(!config.containsKey(enventType))
                    {
                        config.put(enventType,new ArrayList<EnventHandler>());
                    }
                    config.get(enventType).add(entry.getValue());
                }
            }
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Jedis jedis = jedisPool.getResource();
                while (true) {
                    String key = RedisKeyUtils.getEnventQueueKey();
                    List<String> events = jedis.brpop(0, key);
                    for(String event:events){
                        if(event.equals(key))
                        {
                            continue;
                        }
                        EnventModel enventModel = JSON.parseObject(event,EnventModel.class);
                        if(!config.containsKey(enventModel.getEnventType())){
                            logger.error("不能识别事件");
                            System.out.println("不能识别事件");
                            continue;
                        }
                        for(EnventHandler handler:config.get(enventModel.getEnventType())){
                            handler.doHandler(enventModel);
                        }
                    }
                }
            }
        });
        thread.start();

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
