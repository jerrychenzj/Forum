package com.jin.dao;


import com.jin.pojo.Feed;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JINS on 2018/2/28.
 */
public interface FeedDao {
    Feed findFeedById(int id);
    int insertFeed(Feed feed);

    List<Feed> selectFeeds(@Param("maxId") int maxId,
                           @Param("userIds") List<Integer> userIds,
                           @Param("count") int count);

}
