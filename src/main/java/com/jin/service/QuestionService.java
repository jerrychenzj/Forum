package com.jin.service;

import com.jin.pojo.Question;

import java.util.List;

/**
 * Created by JINS on 2018/2/27.
 */
public interface QuestionService {
    int addQuestion(Question question);
    List<Question> selectLatestQuestion(int userid, int offset, int limit);
    Question selectQuestionById(int id);
}
