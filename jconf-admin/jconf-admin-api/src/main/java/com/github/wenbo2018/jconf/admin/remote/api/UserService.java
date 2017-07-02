package com.github.wenbo2018.jconf.admin.remote.api;

import com.github.wenbo2018.jconf.admin.remote.dto.UserDTO;

/**
 * Created by wenbo.shen on 2017/5/19.
 */
public interface UserService {
    int addUser(UserDTO userDTO);
    UserDTO loadUserByUserId(int userId);
    UserDTO loadUserByUserNameAndPassWord(String userName, String passWord);
    UserDTO loadUserByUserName(String userName);
    UserDTO loadUserByToken(String token);
    int updateUserTokenByUserId(String token, int userId);
}
