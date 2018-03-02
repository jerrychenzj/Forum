package com.jin.dao;


import com.jin.pojo.Comment;

/**
 * Created by JINS on 2018/2/28.
 */
public interface CommentDao {
   Comment findCommentById(int id);
    void insertComment(Comment comment);
    void updateComment(Comment comment);
    void deleteCommentById(int id);
}
