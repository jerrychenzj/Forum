package com.jin.async.Handler;

import com.jin.async.EnventHandler;
import com.jin.async.EnventModel;
import com.jin.async.EnventType;
import com.jin.pojo.Feed;
import com.jin.pojo.Message;
import com.jin.pojo.User;
import com.jin.service.FeedService;
import com.jin.service.MessageService;
import com.jin.service.UserService;
import com.jin.utils.MyUtils;
import com.jin.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by JINS on 2018/3/6.
 */
@Component
public class UnFollowHandler implements EnventHandler{
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    JedisPool jedisPool;

    @Autowired
    FeedService feedService;

    @Override
    public void doHandler(EnventModel enventModel) {
          if(enventModel.getEntityType() == MyUtils.ENTITY_USER){
              Jedis jedis = jedisPool.getResource();
              List<Feed> feeds = feedService.getUserFeeds(Integer.MAX_VALUE,Arrays.asList(enventModel.getEntityId()),Integer.MAX_VALUE);
              String timelineKey = RedisKeyUtils.getTimelineKey(enventModel.getActionId());
              for(Feed f:feeds) {
                  jedis.lrem(timelineKey,0 ,String.valueOf(f.getId() ));
              }
              jedis.close();
          }
    }

    @Override
    public List<EnventType> getSupportEnventTypes() {
        return Arrays.asList(EnventType.UNFOLLOWER);
    }
}
