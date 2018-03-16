package com.jin.controller;

import com.alibaba.fastjson.JSONObject;
import com.jin.pojo.Feed;
import com.jin.pojo.HostHolder;
import com.jin.service.FeedService;
import com.jin.service.FollowService;
import com.jin.utils.MyUtils;
import com.jin.utils.RedisKeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JINS on 2018/3/10.
 */
@Controller
@RequestMapping("/Forum/feed")
public class FeedController {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private FollowService followService;

    @Autowired
    private FeedService feedService;

    @Autowired
    private JedisPool jedisPool;

    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

    @RequestMapping("pull")
    public String getPullFeed(Model model,@RequestParam(value = "count",required = false,defaultValue = "10") int count){
        int localUserId = hostHolder.getUser() == null? 0:hostHolder.getUser().getId();
        List<Integer>  followees = new ArrayList<>();
        if(localUserId != 0){
            followees =  followService.getFollowees(localUserId, MyUtils.ENTITY_USER,-1);
        }
         List<Feed> feeds = feedService.getUserFeeds(Integer.MAX_VALUE,followees,count);
        model.addAttribute("feeds",feeds);
        model.addAttribute("count",count);
        return "feeds";
    }

    @RequestMapping("push")
    public String getPushFeed(Model model){
        int localUserId = hostHolder.getUser() == null? 0:hostHolder.getUser().getId();
        Jedis jedis = jedisPool.getResource();

        List<String>  feedIds = jedis.lrange(RedisKeyUtils.getTimelineKey(localUserId),0,10);

        List<Feed> feeds = new ArrayList<>();
        for(String id:feedIds){
            Feed feed = feedService.getFeedById(Integer.valueOf(id));
            if(feed == null){
                continue;
            }
            feeds.add(feed);
        }
        model.addAttribute("feeds",feeds);
        return "feeds";
    }
}
