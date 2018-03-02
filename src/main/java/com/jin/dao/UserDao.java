package com.jin.dao;
import com.jin.pojo.User;
/**
 * Created by JINS on 2018/2/27.
 */
public interface UserDao {
     User findUserById(int id);
     User findUserByName(String name);
     User FindUserByNameAndPassword(String name,String password);
     void insertUser(User user);
     void updateUser(User user);
     void deleteUserById(int id);
     void deleteUser(User user);
}
