package com.jin.service;

import com.jin.pojo.Question;

import java.util.Date;
import java.util.List;

/**
 * Created by JINS on 2018/3/15.
 */
public interface SearchService {
    List<Question> searchQuestion(String keyWord,int offset,int count,String hlPre,String hlpos) throws Exception;
    boolean addIndexQuestion(int id, String title, String content) throws Exception;
}
