package com.jin.service;
import com.jin.pojo.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by JINS on 2018/2/27.
 */
public interface UserService {
     User findUserById(int id);
     Map register(String name,String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;
     Map login(String name,String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;
     void logout(String ticket);
     User findUserByName(String name);
}
