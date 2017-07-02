package com.github.wenbo2018.jconf.services;

import com.github.wenbo2018.jconf.admin.remote.api.UserService;
import com.github.wenbo2018.jconf.admin.remote.dto.UserDTO;
import com.github.wenbo2018.jconf.common.util.StringUtils;
import com.github.wenbo2018.jconf.dao.UserDao;
import com.github.wenbo2018.jconf.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wenbo.shen on 2017/7/2.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(UserDTO userDTO) {
        if (userDTO==null) {
            return -1;
        }
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        return userDao.addUser(user);
    }

    @Override
    public UserDTO loadUserByUserId(int userId) {
        if (userId <= 0) {
            return null;
        }
        User user=userDao.loadUserByUserId(userId);
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    @Override
    public UserDTO loadUserByUserNameAndPassWord(String userName, String passWord) {
        User user = null;
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            return null;
        }
        user = userDao.loadUserByUserNameAndPassWord(userName, passWord);
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    @Override
    public UserDTO loadUserByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        User user= userDao.loadUserByUserName(userName);
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    @Override
    public UserDTO loadUserByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user= userDao.loadUserByToken(token);
        if (user==null) {
            return null;
        }
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    @Override
    public int updateUserTokenByUserId(String token, int userId) {
        return userDao.updateUserTokenByUserId(token,userId);
    }
}
