package com.jin.controller;

import com.jin.async.EnventModel;
import com.jin.async.EnventProducer;
import com.jin.async.EnventType;
import com.jin.pojo.Comment;
import com.jin.pojo.HostHolder;
import com.jin.service.CommentService;
import com.jin.service.QuestionService;
import com.jin.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by JINS on 2018/3/1.
 */
@Controller
@RequestMapping("Forum/comment")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    QuestionService questionService;

    @Autowired
    EnventProducer enventProducer;

    @RequestMapping("add")
    @ResponseBody
    public String addComment(Model model,@RequestParam("content") String content,@RequestParam("entity_id") int entity_id,
                             @RequestParam("entity_type") int entity_type){
        try {
            Comment comment = new Comment();
            comment.setContent(content);
            if (hostHolder.getUser() != null) {
                comment.setUser_id(hostHolder.getUser().getId());
            } else {
                comment.setUser_id(MyUtils.ANONYMOUS_USERNAME);
            }
            comment.setCreated_date(new Date());
            comment.setEntity_id(entity_id);
            comment.setEntity_type(entity_type);
            comment.setStatus(MyUtils.DEFAULT_STATUS);

            enventProducer.fireEvent(new EnventModel(EnventType.COMMENT).setActionId(comment.getUser_id())
                    .setEntityId(entity_id).setEntityType(entity_type));

            commentService.insertComment(comment);

            int count = commentService.getCommentCount(entity_id,MyUtils.ENTITY_QUESTION);
            questionService.updateQuestionCount(entity_id,count);
            return MyUtils.getJSONString(0,"成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
       // return "redirect:/Forum/question/detail/"+entity_id+".do";
        return MyUtils.getJSONString(1,"失败");
    }


}
