package com.jin.async.Handler;

import com.alibaba.fastjson.JSONObject;
import com.jin.async.EnventHandler;
import com.jin.async.EnventModel;
import com.jin.async.EnventType;
import com.jin.pojo.Feed;
import com.jin.pojo.Message;
import com.jin.pojo.Question;
import com.jin.pojo.User;
import com.jin.service.*;
import com.jin.utils.MyUtils;
import com.jin.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * Created by JINS on 2018/3/6.
 */
@Component
public class FeedHandler implements EnventHandler{
    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @Autowired
    FeedService feedService;

    @Autowired
    FollowService followService;

    @Autowired
    JedisPool jedisPool;

    @Override
    public void doHandler(EnventModel enventModel) {
        Feed feed = new Feed();
        feed.setCreated_date(new Date());
        feed.setType(enventModel.getEnventType().getValue());
        feed.setUser_id(enventModel.getActionId());
        feed.setData(buildFeedData(enventModel));
        if(feed.getData() == null){
            return;
        }
        feedService.addFeed(feed);

        List<Integer> followers = followService.getFollowers(feed.getUser_id(),MyUtils.ENTITY_USER,-1);
        followers.add(0);
        Jedis jedis = jedisPool.getResource();
        for(int id:followers){
            String timelineKey = RedisKeyUtils.getTimelineKey(id);
            jedis.lpush(timelineKey,String.valueOf(feed.getId()));
        }
        jedis.close();

    }

    private String buildFeedData(EnventModel model){
        Map<String,String> map = new HashMap<>();
        User user = userService.findUserById(model.getActionId());
        if (user == null)
        {
            return null;
        }
        map.put("userId",String.valueOf(user.getId()));
        map.put("userHeadUrl",user.getHead_url());
        map.put("userName",user.getName());

        if(model.getEnventType() == EnventType.COMMENT||model.getEnventType() == EnventType.FOLLOWER
                &&model.getEntityType() == MyUtils.ENTITY_QUESTION){
            Question question = questionService.selectQuestionById(model.getEntityId());
            if(question == null){
                return null;
            }
            map.put("questionId",String.valueOf(question.getId()));
            map.put("questionTitle",question.getTitle());
        }
        if(model.getEnventType() == EnventType.LIKE){
            Question question = questionService.selectQuestionById(Integer.valueOf(model.getExt("questionid")));
            if(question == null){
                return null;
            }
            map.put("questionId",String.valueOf(question.getId()));
            map.put("questionTitle",question.getTitle());
            User cu = userService.findUserById(model.getEntityOwnerId());
            if(cu == null){ return null;}
            map.put("commentUserName",cu.getName());
            map.put("commentUserId",String.valueOf(cu.getId()));
        }
        return JSONObject.toJSONString(map);
    }

    @Override
    public List<EnventType> getSupportEnventTypes() {
        return Arrays.asList(new EnventType[]{EnventType.COMMENT,EnventType.FOLLOWER,EnventType.LIKE});
    }
}
