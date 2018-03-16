package com.jin.service.impl;

import com.jin.dao.CommentDao;
import com.jin.pojo.Comment;
import com.jin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by JINS on 2018/2/27.
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDao commentDao;

    @Autowired
    SensitiveService sensitiveService;

    @Override
    public List<Comment> selectCommentByEntity(int entity_id, int entity_type) {
        return commentDao.selectCommentByEntity(entity_id,entity_type);
    }

    @Override
    public int getCommentCount(int entity_id, int entity_type) {
        return commentDao.getCommentCount(entity_id,entity_type);
    }

    @Override
    public void deleteComment(int id, int status) {
          commentDao.updateCommentStatus(id,1);
    }

    @Override
    public void insertComment(Comment comment) {

        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveService.filter(comment.getContent()));
        commentDao.insertComment(comment);
    }

    @Override
    public Comment findCommentById(int id) {
        return commentDao.findCommentById(id);
    }

    @Override
    public int getCommentCountByUser(int user_id) {
        return commentDao.getCommentCountByUser(user_id);
    }

    @Override
    public List<Comment> getCommentsByUserId(int user_id) {
        return commentDao.getCommentsByUserId(user_id);
    }
}
