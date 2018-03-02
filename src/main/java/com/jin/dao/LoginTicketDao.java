package com.jin.dao;

import com.jin.pojo.LoginTicket;
import org.apache.ibatis.annotations.Param;

/**
 * Created by JINS on 2018/2/28.
 */
public interface LoginTicketDao {
    LoginTicket selectByTicket(String ticket);
    void insertTicket(LoginTicket loginTicket);
    void updateTicket(@Param("ticket") String ticket,@Param("status") int status);
}
