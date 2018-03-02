package com.jin.service.impl;

import com.jin.dao.QuestionDao;
import com.jin.pojo.Question;
import com.jin.service.QuestionService;
import com.jin.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by JINS on 2018/2/27.
 */
@Service
public class QuestionServiceImpl implements QuestionService{

    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Autowired
    QuestionDao dao;

    @Autowired
    SensitiveService sensitiveService;

    @Override
    public int addQuestion(Question question) {
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));

          question.setTitle(sensitiveService.filter(question.getTitle()));
          question.setContent(sensitiveService.filter(question.getContent()));


        try {
                dao.insertQuestion(question);
                    return question.getUser_id();
        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.error("addQuestion出现错误："+e.getMessage());
                   return 0;
        }
    }

    @Override
    public List<Question> selectLatestQuestion(int userid, int offset, int limit) {
        return dao.selectLatestQuestion(userid,offset,limit);
    }

    @Override
    public Question selectQuestionById(int id) {
     return     dao.findQuestionById(id);
    }
}
