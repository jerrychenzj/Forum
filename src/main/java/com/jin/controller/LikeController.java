package com.jin.controller;

import com.jin.async.EnventModel;
import com.jin.async.EnventProducer;
import com.jin.async.EnventType;
import com.jin.pojo.Comment;
import com.jin.pojo.HostHolder;
import com.jin.service.CommentService;
import com.jin.service.LikeService;
import com.jin.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JINS on 2018/3/5.
 */
@Controller
@RequestMapping("/Forum")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private  HostHolder hostHolder;

    @Autowired
    private EnventProducer enventProducer;

    @Autowired
    CommentService commentService;

    @RequestMapping("like")
    @ResponseBody
    public String like(@RequestParam("entityid") int entityid,@RequestParam("entitytype") int entitytype){
             if(hostHolder.getUser() ==null){
               return  MyUtils.getJSONString(999,"用户未登录");
             }

        Comment comment = commentService.findCommentById(entityid);
             enventProducer.fireEvent(new EnventModel(EnventType.LIKE).setActionId(hostHolder.getUser().getId())
                     .setEntityId(entitytype).setEntityId(entityid).setEntityOwnerId(comment.getUser_id())
                     .setExt("questionid",String.valueOf(comment.getEntity_id())));

             long count = likeService.like(hostHolder.getUser().getId(),entitytype,entityid);

        return MyUtils.getJSONString(0,String.valueOf(count));

    }

    @RequestMapping("dislike")
    @ResponseBody
    public String dislike(@RequestParam("entityid") int entityid,@RequestParam("entitytype") int entitytype){
        if(hostHolder.getUser() ==null){
            return  MyUtils.getJSONString(999,"用户未登录");
        }

        long count = likeService.dislike(hostHolder.getUser().getId(),entitytype,entityid);

        return MyUtils.getJSONString(0,String.valueOf(count));

    }
}
