package com.jin.service;

import com.jin.pojo.Message;

import java.util.List;

/**
 * Created by JINS on 2018/2/27.
 */
public interface MessageService {
    int insertMessage(Message message);
    List<Message> getConversationDetail(String conversation_id, int offset, int limit);
    List<Message> getConversationList(int userid, int offset, int limit);
    int getConversationUnReadCount(int toid,String conversation_id);
    void updatereadstatus(int toid, String conversation_id);
}
