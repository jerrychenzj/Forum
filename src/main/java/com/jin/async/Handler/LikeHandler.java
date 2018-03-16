package com.jin.async.Handler;

import com.jin.async.EnventHandler;
import com.jin.async.EnventModel;
import com.jin.async.EnventType;
import com.jin.pojo.Message;
import com.jin.pojo.User;
import com.jin.service.MessageService;
import com.jin.service.UserService;
import com.jin.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by JINS on 2018/3/6.
 */
@Component
public class LikeHandler implements EnventHandler{
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandler(EnventModel enventModel) {
        Message message = new Message();
        message.setFromid(MyUtils.SYSTEM_USERNAME);
        message.setToid(enventModel.getEntityOwnerId());
        message.setCreated_date(new Date());
        message.setConversation_id(message.getConversation_id());
        message.setHasread(0);
        User user = userService.findUserById(enventModel.getActionId());
        message.setContent("用户:"+user.getName()+"赞了你的评论，http://localhost:8088/Forum/question/detail/"+enventModel.getExt("questionid")+".do");

        messageService.insertMessage(message);
    }

    @Override
    public List<EnventType> getSupportEnventTypes() {
        return Arrays.asList(EnventType.LIKE);
    }
}
