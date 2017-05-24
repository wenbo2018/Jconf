package com.github.wenbo2018.jconf.web.web;

import com.github.wenbo2018.jconf.web.bean.CommonResultJson;
import com.github.wenbo2018.jconf.web.constants.ResultCode;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.tools.corba.se.idl.StringGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by wenbo.shen on 2017/5/19.
 */
@Controller
@RequestMapping(value = "/jconf/user")
public class UserController {



    @RequestMapping(value = "login/index",method = RequestMethod.GET)
    public String loginIndex() {
        return "user/index";
    }

    public CommonResultJson login(String userName,String passWord) {
        CommonResultJson result=new CommonResultJson();
        if (StringUtils.isEmpty(userName)||StringUtils.isEmpty(passWord)) {
            result.setMessage("用户名或密码不能问空!");
            result.setCode(ResultCode.PARAMETER_ERROR);
            return result;
        }
        return result;
    }

    private boolean isLegalUser(String userName,String passWord) {
         return true;
    }

}
