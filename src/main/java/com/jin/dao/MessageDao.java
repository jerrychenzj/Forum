package com.jin.dao;

import com.jin.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by JINS on 2018/2/27.
 */
public interface MessageDao {
    Message findMessageById(int id);
    void insertMessage(Message message);
    void updateMessage(Message message);
    void deleteMessageById(int id);
    List<Message> getConversationDetail(@Param("conversation_id") String conversation_id,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);
    List<Message> getConversationList(@Param("userid") int userid,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);

    int getConversationUnReadCount(@Param("toid") int toid,@Param("conversation_id") String conversation_id);
    void updatereadstatus(@Param("toid") int toid,@Param("conversation_id") String conversation_id);
}
