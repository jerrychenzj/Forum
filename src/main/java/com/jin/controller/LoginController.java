package com.jin.controller;

import com.jin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 * Created by JINS on 2018/2/28.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

       @Autowired
       UserService userservice;

       private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

       @RequestMapping("tologin")
       public String toLoginPage(Model model,
                                 @RequestParam(value = "next",required = false) String next){
           model.addAttribute("next",next);
           return "login";
       }

       @RequestMapping("reg")
       public String register(HttpSession httpSession,String name,String check_code, String password, Model model, HttpServletResponse response,
                              @RequestParam(value = "next",required = false) String next){
           try {
               String checkcode=(String) httpSession.getAttribute("checkcode_session");
               if(!check_code.equals(checkcode))
               {
                   model.addAttribute("msg","验证码错误");
                   return "login";
               }
               Map<String, String> map = userservice.register(name, password);
               if (map.containsKey("ticket")) {
                   Cookie cookie  = new Cookie("ticket",map.get("ticket"));
                   cookie.setPath("/");
                   response.addCookie(cookie);
                   if(!"".equals(next))
                       return "redirect:"+next;
                   else
                       return "redirect:/Forum/home.do";
               }else{
                   model.addAttribute("msg", map.get("msg"));
                   return "login";
               }

           }catch (Exception exp){
                 logger.error("注册异常"+exp.getMessage());
                 return "login";
           }
       }

       @RequestMapping("loginaction")
       public String loginAction(HttpSession httpSession,String name,String check_code, String password, Model model, HttpServletResponse response,
                                 @RequestParam(value = "rememberme" ,defaultValue ="false") boolean rememberme,
                                  @RequestParam(value = "next",required = false) String next){
           try {
               String checkcode=(String) httpSession.getAttribute("checkcode_session");
               if(!check_code.equals(checkcode))
               {
                   model.addAttribute("msg","验证码错误");
                   return "login";
               }
               Map<String, String> map = userservice.login(name, password);
               if (map.containsKey("ticket")) {
                   Cookie cookie  = new Cookie("ticket",map.get("ticket"));
                   cookie.setPath("/");
                   response.addCookie(cookie);
                   if(!"".equals(next))
                       return "redirect:"+next;
                   else
                       return "redirect:/Forum/home.do";
               }else{
                   model.addAttribute("msg", map.get("msg"));
                   return "login";
               }

           }catch (Exception e){
               logger.error("登录异常"+e.getMessage());
               System.out.println(e.getMessage());
               return "login";
           }
       }

       @RequestMapping("logout")
       public String loginout(@CookieValue("ticket") String ticket){
           userservice.logout(ticket);
           return "redirect:/Forum/home.do";
       }
}
