package com.jin.service;

import com.jin.pojo.Comment;

import java.util.List;

/**
 * Created by JINS on 2018/2/27.
 */
public interface CommentService {
    List<Comment> selectCommentByEntity(int entity_id,int entity_type);
    int getCommentCount(int entity_id,int entity_type);
    void deleteComment(int id,int status);
    void insertComment(Comment comment);
    Comment findCommentById(int id);
    int getCommentCountByUser(int user_id);
    List<Comment> getCommentsByUserId(int user_id);
}
