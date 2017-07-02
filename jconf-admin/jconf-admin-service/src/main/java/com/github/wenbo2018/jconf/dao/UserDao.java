package com.github.wenbo2018.jconf.dao;

import com.github.wenbo2018.jconf.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wenbo.shen on 2017/5/19.
 */
public interface UserDao {
    int addUser(@Param("user") User user);
    User loadUserByUserId(@Param("userId") int userId);
    User loadUserByUserNameAndPassWord(@Param("userName") String userName, @Param("passWord") String passWord);
    User loadUserByUserName(@Param("userName") String userName);
    User loadUserByToken(@Param("token") String token);
    int updateUserTokenByUserId(@Param("token") String token, @Param("userId") int userId);
}
