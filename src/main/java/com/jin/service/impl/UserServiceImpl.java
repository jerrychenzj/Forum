package com.jin.service.impl;

import com.jin.dao.LoginTicketDao;
import com.jin.dao.UserDao;
import com.jin.pojo.LoginTicket;
import com.jin.pojo.User;
import com.jin.service.UserService;
import com.jin.utils.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by JINS on 2018/2/27.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Autowired
    private LoginTicketDao ltdao;


    @Override
    public User findUserById(int id) {
        return dao.findUserById(id);
    }

    @Override
    public Map register(String name,String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String,String> map = new HashMap<>();
        if(StringUtils.isBlank(name))
        {
                 map.put("msg","用户名不能为空");
                 return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("msg","密码不能为空");
            return map;
        }
        User user = dao.findUserByName(name);
        if (user != null){
            map.put("msg","用户名已经被注册");
        }

        user = new User();
        user.setName(name);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setHead_url(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        user.setPassword(MyUtils.EncoderByMd5(password+user.getSalt()));
        dao.insertUser(user);

        String ticket = addTicket(user.getId());
        map.put("ticket",ticket);


        return map;
}
    @Override
    public Map login(String name,String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String,String> map = new HashMap<>();
        if(StringUtils.isBlank(name))
        {
            map.put("msg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("msg","密码不能为空");
            return map;
        }
        User user = dao.findUserByName(name);
        if (user == null){
            map.put("msg","用户名没有注册");
            return map;
        }

       if(!MyUtils.EncoderByMd5(password+user.getSalt()).equals(user.getPassword())){
            map.put("msg","密码错误");
            return map;
       }

        String ticket = addTicket(user.getId());
        map.put("ticket",ticket);


        return map;
    }

    @Override
    public void logout(String ticket) {
        ltdao.updateTicket(ticket,1);
    }



      public String addTicket(int userid){
          LoginTicket lt = new LoginTicket();
          lt.setStatus(0);
          Date now = new Date();
          now.setTime(3600*24*10+now.getTime());
          lt.setExpired(now);
          lt.setUserid(userid);
          lt.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
          ltdao.insertTicket(lt);
          return lt.getTicket();
      }
}
