package com.jin.dao;

import com.jin.pojo.Message;

/**
 * Created by JINS on 2018/2/27.
 */
public interface MessageDao {
    Message findMessageById(int id);
    void insertMessage(Message message);
    void updateMessage(Message message);
    void deleteMessageById(int id);
}
