package com.jin.controller;

import com.jin.pojo.Question;
import com.jin.pojo.User;
import com.jin.service.*;
import com.jin.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JINS on 2018/3/15.
 */
@Controller
@RequestMapping("/Forum")
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;

    @Autowired
    UserService userService;

    @RequestMapping("search")
    public String search(Model model, @RequestParam("keyWord") String keyWord,
                         @RequestParam(value = "offset",defaultValue = "0",required = false) int offset,
                         @RequestParam(value = "count",defaultValue = "10",required = false) int count ){
        try {
            List<Question> list = searchService.searchQuestion(keyWord,offset,count,"<font style='color:red' >","</font>");
            if (list.size()==0 && offset !=0){
                offset = offset-10;
               list = searchService.searchQuestion(keyWord,offset,count,"<font style='color:red' >","</font>");
            }
            List<Map<String,Object>> searchs = new ArrayList<>();
            for(Question q:list){
                Map<String,Object> map = new HashMap<>();
                Question question= questionService.selectQuestionById(q.getId());
                q.setCreated_date(question.getCreated_date());
                int commentCountcount = commentService.getCommentCount(q.getId(), MyUtils.ENTITY_QUESTION);
                q.setComment_count(commentCountcount);
                String content = q.getContent();
                q.setContent(content.length()<=500?content:content.substring(0,500));
                questionService.updateQuestionCount(q.getId(),commentCountcount);
                map.put("question",q);
                map.put("followers",followService.getFollowerCount(q.getId(),MyUtils.ENTITY_QUESTION));
                User user = userService.findUserById(q.getUser_id());
                map.put("user",user);
                searchs.add(map);
            }
             model.addAttribute("searchs",searchs);
            model.addAttribute("offset",offset);
            model.addAttribute("keyWord",keyWord);
            return "result";

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
             return "redirect:/Forum/home.do";
    }
}
