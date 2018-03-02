package com.jin.controller;

import com.jin.pojo.Question;
import com.jin.service.QuestionService;
import com.jin.service.UserService;
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

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/home")
    public String gethomepage(Model model)
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
        List<Question> questions = questionService.selectLatestQuestion(0,0,10);
        List<HashMap> vos = new ArrayList<>();
        for(Question question:questions){
            HashMap map = new HashMap();
            map.put("question",question);
            map.put("user",userService.findUserById(question.getUser_id()));
             vos.add(map);
        }
        model.addAttribute("vos",vos);

        return "index";
    }

    @RequestMapping("/userhome/{id}")
    public String getuserhomepage(Model model,@PathVariable("id") int id){
        List<Question> questions = questionService.selectLatestQuestion(id,0,10);
        List<HashMap> vos = new ArrayList<>();
        for(Question question:questions){
            HashMap map = new HashMap();
            map.put("question",question);
            map.put("user",userService.findUserById(question.getUser_id()));
            vos.add(map);
        }
        model.addAttribute("vos",vos);

        return "index";
    }
}
