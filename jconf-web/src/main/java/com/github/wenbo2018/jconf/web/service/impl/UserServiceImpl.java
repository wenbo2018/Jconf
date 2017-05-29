package com.github.wenbo2018.jconf.web.service.impl;

import com.github.wenbo2018.jconf.common.util.StringUtils;
import com.github.wenbo2018.jconf.web.dao.UserDao;
import com.github.wenbo2018.jconf.web.dto.User;
import com.github.wenbo2018.jconf.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wenbo.shen on 2017/5/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User loadUserByUserId(int userId) {
        if (userId <= 0) {
            return null;
        }
        return userDao.loadUserByUserId(userId);
    }

    @Override
    public User loadUserByUserNameAndPassWord(String userName, String passWord) {
        User user = null;
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            return null;
        }
        user = userDao.loadUserByUserNameAndPassWord(userName, passWord);
        return user;
    }

    @Override
    public User loadUserByUserName(String userName) {
        return userDao.loadUserByUserName(userName);
    }

    @Override
    public User loadUserByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return userDao.loadUserByToken(token);
    }

    @Override
    public int updateUserTokenByUserId(String token, int userId) {
        return userDao.updateUserTokenByUserId(token,userId);
    }
}
