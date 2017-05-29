package com.github.wenbo2018.jconf.web.service;

import com.github.wenbo2018.jconf.web.dto.User;

/**
 * Created by wenbo.shen on 2017/5/19.
 */
public interface UserService {
    int addUser(User user);
    User loadUserByUserId(int userId);
    User loadUserByUserNameAndPassWord(String userName,String passWord);
    User loadUserByUserName(String userName);
    User loadUserByToken(String token);
    int updateUserTokenByUserId(String token,int userId);
}
