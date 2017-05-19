package com.github.wenbo2018.jconf.web.dao;

import com.github.wenbo2018.jconf.web.dto.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wenbo.shen on 2017/5/19.
 */
public interface UserDao {

    int addUser(User user);
    void delete(int userId);
    User loadUserByUserId(int userId);
    User loadUserByUserNameAndPassWord(@Param("userName") String userName, @Param("passWord")String passWord);

}
