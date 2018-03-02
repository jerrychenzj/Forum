package com.jin.controller;

import com.jin.pojo.HostHolder;
import com.jin.pojo.Question;
import com.jin.service.QuestionService;
import com.jin.service.UserService;
import com.jin.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
             return "detail";
    }
}
