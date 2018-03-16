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
   /*     Random r = new Random();
        for(int i =0;i<10;i++){
            User user = new User();
            user.setHead_url(String.format("http://images.nowcoder.com/head/%dt.png",r.nextInt(1000)));
            user.setName(String.format("user%d",i));
            user.setPassword(""+i);
            user.setSalt(""+i);
            userService.addUser(user);

            Question question = new Question();
            question.setUser_id(i+1);
            question.setComment_count(i);
            question.setContent(String.format("Blalalallalalala%d",i));
            Date date = new Date();
            date.setTime(date.getTime()+1000*3600*i);
            question.setCreated_date(date);
            question.setTitle(String.format("TITLE%d",i));
            questionService.addQuestion(question);

        }*/
        List<Question> questions = questionService.selectLatestQuestion(0,offset,10);
        if (questions.size()==0 && offset !=0){
            offset = offset-10;
            questions = questionService.selectLatestQuestion(0,offset,10);
        }
        List<HashMap> vos = new ArrayList<>();
        for(Question question:questions){
            HashMap map = new HashMap();
            String content = question.getContent();
            question.setContent(content.length()<=500?content:content.substring(0,500));
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
            String content = question.getContent();
            question.setContent(content.length()<=500?content:content.substring(0,500));
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
