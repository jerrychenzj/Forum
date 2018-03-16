package com.jin.service;

import com.jin.pojo.Feed;

import java.util.List;

/**
 * Created by JINS on 2018/3/10.
 */
public interface FeedService {
     Feed getFeedById(int id);
     int addFeed(Feed feed);
     List<Feed> getUserFeeds(int maxId,List<Integer> userIds,int count);
}
