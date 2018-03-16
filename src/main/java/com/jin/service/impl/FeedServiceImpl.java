package com.jin.service.impl;

import com.jin.dao.FeedDao;
import com.jin.pojo.Feed;
import com.jin.service.FeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JINS on 2018/3/10.
 */
@Service
public class FeedServiceImpl implements FeedService{
    private static final Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);

    @Autowired
    private FeedDao feedDao;

    @Override
    public Feed getFeedById(int id) {
        return feedDao.findFeedById(id);
    }

    @Override
    public int addFeed(Feed feed) {
          return feedDao.insertFeed(feed);
    }

    @Override
    public List<Feed> getUserFeeds(int maxId, List<Integer> userIds, int count) {
        return feedDao.selectFeeds(maxId,userIds,count);
    }
}
