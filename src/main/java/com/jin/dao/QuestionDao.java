package com.jin.dao;

import com.jin.pojo.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JINS on 2018/2/27.
 */
public interface QuestionDao {
    Question findQuestionById(int id);
    void insertQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestionById(int id);
    List<Question> selectLatestQuestion(@Param("userid") int userid,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);
    void updateQuestionCount(@Param("id") int id,@Param("comment_count") int comment_count);
}
