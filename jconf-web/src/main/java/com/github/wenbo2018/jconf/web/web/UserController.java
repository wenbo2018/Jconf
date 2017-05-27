package com.github.wenbo2018.jconf.web.web;

import com.github.wenbo2018.jconf.common.util.StringUtils;
import com.github.wenbo2018.jconf.web.bean.CommonResultJson;
import com.github.wenbo2018.jconf.web.constants.ResultCode;
import com.github.wenbo2018.jconf.web.dto.User;
import com.github.wenbo2018.jconf.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wenbo.shen on 2017/5/19.
 */
@Controller
@RequestMapping(value = "/jconf/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login/index",method = RequestMethod.GET)
    public String loginIndex() {
        return "user/index";
    }

    @ResponseBody
    @RequestMapping(value = "login/checkUser",method = RequestMethod.POST)
    public CommonResultJson login(String userName,String passWord) {
        CommonResultJson result=new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (StringUtils.isEmpty(userName)||StringUtils.isEmpty(passWord)) {
            result.setMessage("用户名或密码不能问空!");
            result.setCode(ResultCode.PARAMETER_ERROR);
            return result;
        }
        if (!isLegalUser(userName,passWord)) {
            result.setMessage("用户名或密码错误!");
            result.setCode(ResultCode.PARAMETER_ERROR);
            return result;
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "login/userSignIn",method = RequestMethod.POST)
    public CommonResultJson signIn(String userName,String passWord,String email) {
        CommonResultJson result=new CommonResultJson();
        result.setCode(ResultCode.SUCCESS);
        if (userService.loadUserByUserName(userName)==null) {
           result.setCode(ResultCode.PARAMETER_ERROR);
           result.setMessage("用户名已存在!");
           return result;
        }
        User user=new User();
        user.setUsername(userName);
        user.setPassword(passWord);
        user.setEmail(email);
        userService.addUser(user);
        return result;
    }


    private boolean isLegalUser(String userName,String passWord) {
        User user=userService.loadUserByUserNameAndPassWord(userName,passWord);
        if (user==null) {
            return false;
        }
        return true;
    }

}
