package com.github.wenbo2018.jconf.web.service.impl;

import com.github.wenbo2018.jconf.web.dto.User;
import com.github.wenbo2018.jconf.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wenbo.shen on 2017/5/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserService userService;

    @Override
    public int addUser(User user) {
        return userService.addUser(user);
    }

    @Override
    public User loadUserByUserId(int userId) {
        if (userId<=0) {
            return null;
        }
        return userService.loadUserByUserId(userId);
    }

    @Override
    public User loadUserByUserNameAndPassWord(String userName, String passWord) {
        if (StringUtils.isEmpty(userName)||StringUtils.isEmpty(passWord)) {
            return null;
        }
        return userService.loadUserByUserNameAndPassWord(userName,passWord);
    }
}
