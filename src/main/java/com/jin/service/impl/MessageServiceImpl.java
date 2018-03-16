package com.jin.service.impl;

import com.jin.dao.MessageDao;
import com.jin.pojo.Message;
import com.jin.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JINS on 2018/2/27.
 */
@Service
public class MessageServiceImpl implements MessageService{

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    MessageDao messageDao;

    @Override
    public int insertMessage(Message message) {
        try {
            messageDao.insertMessage(message);
            return 1;
        }catch (Exception e){
            logger.error("插入信息失败"+e.getMessage());
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Message> getConversationDetail(String conversation_id, int offset, int limit) {
      return   messageDao.getConversationDetail(conversation_id,offset,limit);

    }

    @Override
    public List<Message> getConversationList(int userid, int offset, int limit) {
        return messageDao.getConversationList(userid,offset,limit);
    }

    @Override
    public int getConversationUnReadCount(int toid, String conversation_id) {
        return messageDao.getConversationUnReadCount(toid,conversation_id);
    }

    @Override
    public void updatereadstatus(int toid, String conversation_id) {
        messageDao.updatereadstatus(toid,conversation_id);
    }
}
