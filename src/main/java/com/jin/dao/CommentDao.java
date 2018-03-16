package com.jin.dao;


import com.jin.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JINS on 2018/2/28.
 */
public interface CommentDao {
   Comment findCommentById(int id);
    void insertComment(Comment comment);
    void updateCommentStatus(@Param("id") int id,@Param("status") int status);
    void deleteCommentById(int id);
    List<Comment> selectCommentByEntity(@Param("entity_id") int entity_id,
                                        @Param("entity_type") int entity_type);
    int getCommentCount(@Param("entity_id") int entity_id,
                        @Param("entity_type") int entity_type);
    int getCommentCountByUser(int user_id);
    List<Comment> getCommentsByUserId(int user_id);
}
