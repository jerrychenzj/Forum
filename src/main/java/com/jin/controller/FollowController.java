package com.jin.controller;

import com.jin.async.EnventModel;
import com.jin.async.EnventProducer;
import com.jin.async.EnventType;
import com.jin.pojo.Comment;
import com.jin.pojo.HostHolder;
import com.jin.pojo.Question;
import com.jin.pojo.User;
import com.jin.service.*;
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

import java.util.*;

/**
 * Created by JINS on 2018/3/1.
 */
@Controller
@RequestMapping("/Forum")
public class FollowController {
    private static final Logger logger = LoggerFactory.getLogger(FollowController.class);


    @Autowired
    HostHolder hostHolder;

    @Autowired
    EnventProducer enventProducer;

    @Autowired
    FollowService followService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @RequestMapping("followUser")
    @ResponseBody
    public String followUser(Model model,@RequestParam("userId") int userId) {
              if(hostHolder.getUser()==null){
                  return MyUtils.getJSONString(999,"用户未登录");
              }
        boolean ret = followService.follow(hostHolder.getUser().getId(),userId,MyUtils.ENTITY_USER);
        enventProducer.fireEvent(new EnventModel(EnventType.FOLLOWER).setActionId(hostHolder.getUser().getId())
                .setEntityId(userId).setEntityType(MyUtils.ENTITY_USER).setEntityOwnerId(userId));
        return  MyUtils.getJSONString(ret?0:1,String.valueOf(followService
                .getFolloweeCount(hostHolder.getUser().getId(),MyUtils.ENTITY_USER)));
    }

    @RequestMapping("unfollowUser")
    @ResponseBody
    public String unfollowUser(Model model,@RequestParam("userId") int userId) {
        if(hostHolder.getUser()==null){
            return MyUtils.getJSONString(999,"用户未登录");
        }
        enventProducer.fireEvent(new EnventModel(EnventType.UNFOLLOWER).setActionId(hostHolder.getUser().getId())
                .setEntityId(userId).setEntityType(MyUtils.ENTITY_USER).setEntityOwnerId(userId));
        boolean ret = followService.unfollow(hostHolder.getUser().getId(),userId,MyUtils.ENTITY_USER);
        return  MyUtils.getJSONString(ret?0:1,String.valueOf(followService
                .getFolloweeCount(hostHolder.getUser().getId(),MyUtils.ENTITY_USER)));
    }

    @RequestMapping("followQuestion")
    @ResponseBody
    public String followQuestion(Model model,@RequestParam("questionId") int questionId) {
        if(hostHolder.getUser()==null){
            return MyUtils.getJSONString(999,"用户未登录");
        }
       Question question = questionService.selectQuestionById(questionId);
        if (question == null){
            return MyUtils.getJSONString(1,"问题不存在");
        }

        boolean ret = followService.follow(hostHolder.getUser().getId(),questionId,MyUtils.ENTITY_QUESTION);
        enventProducer.fireEvent(new EnventModel(EnventType.FOLLOWER).setActionId(hostHolder.getUser().getId())
                .setEntityId(questionId).setEntityType(MyUtils.ENTITY_QUESTION).setEntityOwnerId(question.getUser_id()));

        Map<String,Object> info = new HashMap<>();
        info.put("head_url",hostHolder.getUser().getHead_url());
        info.put("name",hostHolder.getUser().getName());
        info.put("id",hostHolder.getUser().getId());
        info.put("count",followService.getFollowerCount(questionId,MyUtils.ENTITY_QUESTION));

        return  MyUtils.getJSONString(ret?0:1,info);
    }

    @RequestMapping("unfollowquestion")
    @ResponseBody
    public String unfollowQuestion(Model model,@RequestParam("questionId") int questionId) {
        if(hostHolder.getUser()==null){
            return MyUtils.getJSONString(999,"用户未登录");
        }

        Question question = questionService.selectQuestionById(questionId);
        if (question == null){
            return MyUtils.getJSONString(1,"问题不存在");
        }

        boolean ret = followService.unfollow(hostHolder.getUser().getId(),questionId,MyUtils.ENTITY_QUESTION);

        enventProducer.fireEvent(new EnventModel(EnventType.UNFOLLOWER).setActionId(hostHolder.getUser().getId())
                .setEntityId(questionId).setEntityType(MyUtils.ENTITY_QUESTION).setEntityOwnerId(question.getUser_id()));

        Map<String,Object> info = new HashMap<>();
        info.put("head_url",hostHolder.getUser().getHead_url());
        info.put("name",hostHolder.getUser().getName());
        info.put("id",hostHolder.getUser().getId());
        info.put("count",followService.getFollowerCount(questionId,MyUtils.ENTITY_QUESTION));

        return  MyUtils.getJSONString(ret?0:1,info);
    }

    @RequestMapping("/followers/{userid}")
    public String followers(Model model,@PathVariable("userid") int userid,@RequestParam(value = "offset",required = false,defaultValue = "0") int offset){
        List<Integer> followersIds = followService.getFollowers(userid,MyUtils.ENTITY_USER,offset,offset+9);
        if (followersIds.size()==0 && offset !=0){
            offset = offset-10;
            followersIds = followService.getFollowers(userid,MyUtils.ENTITY_USER,offset,offset+9);
        }
        if (hostHolder.getUser() != null){
            model.addAttribute("followers",getUserInfos(hostHolder.getUser().getId(),followersIds));
        }else {
            model.addAttribute("followers",getUserInfos(0,followersIds));
        }
        model.addAttribute("followerCount",followService.getFollowerCount(userid,MyUtils.ENTITY_USER));
        model.addAttribute("curUser",userService.findUserById(userid));
        model.addAttribute("offset",offset);
        return "follower";
    }

    @RequestMapping("/followees/{userid}")
    public String followees(Model model,@PathVariable("userid") int userid,@RequestParam(value = "offset",required = false,defaultValue = "0") int offset){
        List<Integer> followeesIds = followService.getFollowees(userid,MyUtils.ENTITY_USER,offset,offset+9);
        if (followeesIds.size()==0 && offset !=0){
            offset = offset-10;
            followeesIds = followService.getFollowees(userid,MyUtils.ENTITY_USER,offset,offset+9);
        }
        if (hostHolder.getUser() != null){
            model.addAttribute("followees",getUserInfos(hostHolder.getUser().getId(),followeesIds));
        }else {
            model.addAttribute("followees",getUserInfos(0,followeesIds));
        }
        model.addAttribute("followeeCount",followService.getFolloweeCount(userid,MyUtils.ENTITY_USER));
        model.addAttribute("curUser",userService.findUserById(userid));
        model.addAttribute("offset",offset);
        return "followee";
    }

    private List<Map<String,Object>> getUserInfos(int localUserId,List<Integer> userIds){
         List<Map<String,Object>> infos = new ArrayList<>();
         for(int id:userIds){
             User user = userService.findUserById(id);
             if(user == null) {
                 continue;
             }
             Map<String,Object> info = new HashMap<>();
           info.put("user",user);
           info.put("commentCount",commentService.getCommentCountByUser(id));
           info.put("followers",followService.getFollowerCount(id,MyUtils.ENTITY_USER));
           info.put("followees",followService.getFolloweeCount(id,MyUtils.ENTITY_USER));
           if (localUserId != 0)
           {
               info.put("followed",followService.isFolloer(localUserId,id,MyUtils.ENTITY_USER));
           }else {
               info.put("followed",false);
           }
           List<Comment> list = commentService.getCommentsByUserId(id);
           int likeCount = 0;
           if(list !=null){

               for(Comment c:list){
                   likeCount += likeService.getLikeCount(MyUtils.ENTITY_COMMENT,c.getId());
               }
           }
               info.put("likecount",likeCount);

           infos.add(info);
         }
         return infos;
    }
}
