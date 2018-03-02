package com.jin.Interceptor;

import com.jin.dao.LoginTicketDao;
import com.jin.dao.UserDao;
import com.jin.pojo.HostHolder;
import com.jin.pojo.LoginTicket;
import com.jin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by JINS on 2018/2/28.
 */
@Component
public class PassPortInterceptor implements HandlerInterceptor{

    @Autowired
    LoginTicketDao loginTicketDao;

    @Autowired
    UserDao  userDao;

    @Autowired
    HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String ticket = null;
        if(request.getCookies()!=null){
            for(Cookie cookie:request.getCookies()){
                if(cookie.getName().equals("ticket")){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if(ticket != null){
            LoginTicket lt = loginTicketDao.selectByTicket(ticket);
            if(lt == null || lt.getStatus()!=0||lt.getExpired().before(new Date())){
                return true;
            }
            User user = userDao.findUserById(lt.getUserid());
            hostHolder.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
         if(modelAndView != null){
             modelAndView.addObject("user",hostHolder.getUser());
         }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            hostHolder.clear();
    }
}
