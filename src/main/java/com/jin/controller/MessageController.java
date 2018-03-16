package com.jin.controller;

import com.jin.pojo.HostHolder;
import com.jin.pojo.Message;
import com.jin.pojo.User;
import com.jin.service.MessageService;
import com.jin.service.UserService;
import com.jin.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by JINS on 2018/3/1.
 */
@Controller
@RequestMapping("/Forum/message")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping("addmessage")
    @ResponseBody
    public String addmessage(@RequestParam("toname") String toname,
                              @RequestParam("content") String content){
        try {
            Message message = new Message();
            if(hostHolder.getUser() == null)
            {
                return MyUtils.getJSONString(999,"未登录");
            }
            message.setFromid(hostHolder.getUser().getId());
            User user =  userService.findUserByName(toname);
            if(user == null){
                return MyUtils.getJSONString(1,"用户名不存在");
            }
            message.setToid(user.getId());
            message.setContent(content);
            message.setCreated_date(new Date());
            message.setConversation_id(message.getConversation_id());
            int code=messageService.insertMessage(message);
            if(code>0){
                return MyUtils.getJSONString(0,"成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.error("增加信息失败"+e.getMessage());
        }
        return MyUtils.getJSONString(2,"失败");
    }

    @RequestMapping("list")
    public String messageList(Model model){

        User user = hostHolder.getUser();
        if(user == null){
            return "redirect:/login/tologin.do";
        }
        List<Message> messages = messageService.getConversationList(user.getId(),0,10);
        List<Map> maps = new ArrayList<>();
        for(Message m:messages){
          Map map = new HashMap();
          map.put("message",m);
          int targetId = m.getFromid()==user.getId()?m.getToid():m.getFromid();
          map.put("user",userService.findUserById(targetId));
          map.put("unreadcount",messageService.getConversationUnReadCount(user.getId(),m.getConversation_id()));
          maps.add(map);
        }
        model.addAttribute("maps",maps);
        return "letter";
    }

    @RequestMapping("conversationDetail")
    public String getConversationDetail(Model model,@RequestParam("conversation_id") String conversation_id){

        try {
           List<Message> messages = messageService.getConversationDetail(conversation_id,0,10);
           List<Map> maps = new ArrayList<>();
           for(Message m:messages){
               HashMap map = new HashMap();
               map.put("message",m);
               map.put("user",userService.findUserById(m.getFromid()));
               messageService.updatereadstatus(hostHolder.getUser().getId(),conversation_id);
               maps.add(map);
           }
           model.addAttribute("maps", maps);

        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.error("获取详情信息失败"+e.getMessage());
        }
        return "letterDetail";
    }
}
