package com.jin.utils;

import com.jin.controller.CommentController;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by JINS on 2018/3/1.
 */
public class MyUtils {

    private static final Logger logger = LoggerFactory.getLogger(MyUtils.class);
    public static final  int ANONYMOUS_USERNAME = 1;
    public static final  int SYSTEM_USERNAME = 2;
    public static final  int ENTITY_QUESTION = 1,ENTITY_COMMENT = 2,ENTITY_USER = 3;
    public static final  int DEFAULT_STATUS = 0;
    //发邮件
    public final static String EMAIL_FORM="q453088644@163.com";
    public final static String EMAIL_HOST="smtp.163.com";
    public final static String EMAIL_USERNAME="Forum";
    public final static String EMAIL_PWD="321asdfghjklzxcv";

    //搜索
    public final static String QUESTION_TITLE_FIELD = "question_title";
    public final static String QUESTION_CONTENT_FIELD = "question_content";
    public final static String QUESTION_USERID_FIELD = "question_user_id";
    public final static String QUESTION_CREATEDDATE_FIELD = "question_created_date";

    public static String getJSONString(int code,String msg){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toString();
    }

    public static String getJSONString(int code,Map<String,Object> info){
        JSONObject json = new JSONObject();
        json.put("code",code);
        for (Map.Entry<String,Object> e:info.entrySet()){
            json.put(e.getKey(),e.getValue());
        }
        return json.toString();
    }

    public static String getJSONString(int code){
        JSONObject json = new JSONObject();
        json.put("code",code);
        return json.toString();
    }


    public static  String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
