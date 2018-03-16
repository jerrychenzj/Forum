package com.jin.async.Handler;

import com.alibaba.fastjson.JSONObject;
import com.jin.async.EnventHandler;
import com.jin.async.EnventModel;
import com.jin.async.EnventType;
import com.jin.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

/**
 * Created by JINS on 2018/3/6.
 */
@Component
public class AddIndexHandler implements EnventHandler{

    private static final Logger logger = LoggerFactory.getLogger(AddIndexHandler.class);

    @Autowired
    SearchService searchService;


    @Override
    public void doHandler(EnventModel enventModel) {
        try {
            searchService.addIndexQuestion(enventModel.getEntityId(),enventModel.getExt("title"),enventModel.getExt("content"));

        }catch (Exception e){
            logger.error("添加solr数据库索引失败"+e.getMessage());
        }
    }

    @Override
    public List<EnventType> getSupportEnventTypes() {
        return Arrays.asList(new EnventType[]{EnventType.ADDINDEX});
    }
}
