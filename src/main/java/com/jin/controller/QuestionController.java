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
import com.jin.utils.RedisKeyUtils;
import org.apache.solr.common.util.Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by JINS on 2018/3/1.
 */
@Controller
@RequestMapping("/Forum/question")
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @Autowired
    FollowService followService;

    @Autowired
    EnventProducer enventProducer;

    @RequestMapping("addquestion")
    @ResponseBody
    public String addquestion(@RequestParam("title") String title,
                              @RequestParam("content") String content){
         try {
             Question question = new Question();
             question.setTitle(title);
             question.setContent(content);
             question.setCreated_date(new Date());
             question.setComment_count(0);
             if(hostHolder.getUser()!=null){
                 question.setUser_id(hostHolder.getUser().getId());
             }else {
                 //question.setUser_id(MyUtils.ANONYMOUS_USERNAME);
                 return MyUtils.getJSONString(999,"未登录");
             }
             int code=questionService.addQuestion(question);
             int id = question.getId();
             enventProducer.fireEvent(new EnventModel(EnventType.ADDINDEX).setActionId(question.getUser_id())
                     .setEntityId(question.getId()).setExt("title",question.getTitle()).setExt("content",question.getContent()));

             if(code>0){
                 return MyUtils.getJSONString(0,"成功");
             }
         }catch (Exception e){
             System.out.println(e.getMessage());
             logger.error("增加问题失败"+e.getMessage());
         }
         return MyUtils.getJSONString(1,"失败");
    }

    @RequestMapping("/detail/{id}")
    public String questionDetail(Model model, @PathVariable("id") int id){
             Question question =  questionService.selectQuestionById(id);
             model.addAttribute("question",question);
             model.addAttribute("user",userService.findUserById(question.getUser_id()));
             model.addAttribute("followercount",followService.getFollowerCount(question.getId(),MyUtils.ENTITY_QUESTION));
             List<Integer> list = followService.getFollowers(question.getId(),MyUtils.ENTITY_QUESTION,0,-1);
             List<HashMap> followers = new ArrayList<>();
             for(int c:list) {
                 HashMap follower= new HashMap();
                 User user = userService.findUserById(c);
                 follower.put("userid",c);
                 follower.put("head_url",user.getHead_url());
                 follower.put("name",user.getName());
                 followers.add(follower);
             }
             model.addAttribute("followers",followers);
             List<Comment> comments = commentService.selectCommentByEntity(id,MyUtils.ENTITY_QUESTION);
             List<HashMap> maps = new ArrayList<>();
             for(Comment c:comments){
                 HashMap map = new HashMap();
                 map.put("comment",c);
                 map.put("user",userService.findUserById(c.getUser_id()));
                 map.put("likecount",likeService.getLikeCount(MyUtils.ENTITY_COMMENT,c.getId()));
                 if(hostHolder.getUser()==null){
                     map.put("likestatus",0);
                 }else {
                     map.put("likestatus",likeService.getLikeStatus(hostHolder.getUser().getId(),MyUtils.ENTITY_COMMENT,c.getId()));
                 }

                 maps.add(map);
             }
             model.addAttribute("maps",maps);

       if (hostHolder.getUser() !=null) {
           Map<String, Object> info = new HashMap<>();
           info.put("id", hostHolder.getUser().getId());
           info.put("commentCount", commentService.getCommentCountByUser(question.getUser_id()));
           info.put("followers", followService.getFollowerCount(question.getUser_id(), MyUtils.ENTITY_USER));
           info.put("followees", followService.getFolloweeCount(question.getUser_id(), MyUtils.ENTITY_USER));
           info.put("followed", followService.isFolloer(hostHolder.getUser().getId(), question.getUser_id(), MyUtils.ENTITY_USER));
           List<Comment> clist = commentService.getCommentsByUserId(question.getUser_id());
           int likeCount = 0;
           if (clist != null) {

               for (Comment c : clist) {
                   likeCount += likeService.getLikeCount(MyUtils.ENTITY_COMMENT, c.getId());
               }
           }
           info.put("likecount", likeCount);
           model.addAttribute("info", info);
           model.addAttribute("followed",followService.isFolloer(hostHolder.getUser().getId(),question.getId(),MyUtils.ENTITY_QUESTION));
       }
        model.addAttribute("fromuser",userService.findUserById(question.getUser_id()));

             return "detail";
    }
}
