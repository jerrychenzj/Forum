package com.jin.controller;

import com.jin.pojo.Comment;
import com.jin.pojo.HostHolder;
import com.jin.pojo.Question;
import com.jin.service.*;
import com.jin.utils.MyUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/Forum")
public class HomeController {

    @Autowired
    UserService userService ;

    @Autowired
    QuestionService questionService;

    @Autowired
    FollowService followService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/home")
    public String gethomepage(Model model,@RequestParam(value = "offset",required = false,defaultValue = "0") int offset)
    {
        List<Question> questions = questionService.selectLatestQuestion(0,offset,10);
        if (questions.size()==0 && offset !=0){
            offset = offset-10;
            questions = questionService.selectLatestQuestion(0,offset,10);
        }
        List<HashMap> vos = new ArrayList<>();
        for(Question question:questions){
            HashMap map = new HashMap();
            String content = MyUtils.stripHtml(question.getContent().length()<=500?question.getContent():question.getContent().substring(0,500));
            question.setContent(content);
            map.put("question",question);
            map.put("user",userService.findUserById(question.getUser_id()));
            map.put("followers",followService.getFollowerCount(question.getId(),MyUtils.ENTITY_QUESTION));
            if (hostHolder.getUser() != null)
            {
                map.put("followed",followService.isFolloer(hostHolder.getUser().getId(),question.getId(),MyUtils.ENTITY_QUESTION));
            }else {
                map.put("followed",false);
            }
             vos.add(map);
        }
        model.addAttribute("vos",vos);
        model.addAttribute("offset",offset);

        return "index";
    }

    @RequestMapping("/userhome/{id}")
    public String getuserhomepage(Model model,@PathVariable("id") int id,@RequestParam(value = "offset",required = false,defaultValue = "0") int offset){
        List<Question> questions = questionService.selectLatestQuestion(id,offset,10);
        if (questions.size() == 0 && offset !=0 ){
            offset = offset-10;
            questions = questionService.selectLatestQuestion(0,offset,10);
        }
        List<HashMap> vos = new ArrayList<>();
        for(Question question:questions){
            String content = MyUtils.stripHtml(question.getContent().length()<=500?question.getContent():question.getContent().substring(0,500));
            question.setContent(content);
            HashMap map = new HashMap();
            map.put("question",question);
            map.put("user",userService.findUserById(question.getUser_id()));
            map.put("followers",followService.getFollowerCount(question.getId(),MyUtils.ENTITY_QUESTION));
            if (hostHolder.getUser() != null)
            {
               map.put("followed",followService.isFolloer(hostHolder.getUser().getId(),question.getId(),MyUtils.ENTITY_QUESTION));
            }else {
                map.put("followed",false);
            }
            vos.add(map);
        }
        Map<String,Object> info = new HashMap<>();
        info.put("id",hostHolder.getUser().getId());
        info.put("commentCount",commentService.getCommentCountByUser(id));
        info.put("followers",followService.getFollowerCount(id,MyUtils.ENTITY_USER));
        info.put("followees",followService.getFolloweeCount(id,MyUtils.ENTITY_USER));
        info.put("followed",followService.isFolloer(hostHolder.getUser().getId(),id,MyUtils.ENTITY_USER));
        List<Comment> list = commentService.getCommentsByUserId(id);
        int likeCount = 0;
        if(list !=null){

            for(Comment c:list){
                likeCount += likeService.getLikeCount(MyUtils.ENTITY_COMMENT,c.getId());
            }
        }
        info.put("likecount",likeCount);
        model.addAttribute("info",info);
        model.addAttribute("vos",vos);
        model.addAttribute("fromuser",userService.findUserById(id));
        model.addAttribute("offset",offset);

        return "index";
    }
}
