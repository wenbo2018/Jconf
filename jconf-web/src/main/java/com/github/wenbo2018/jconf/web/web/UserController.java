package com.github.wenbo2018.jconf.web.web;

import com.github.wenbo2018.jconf.common.util.StringUtils;
import com.github.wenbo2018.jconf.web.bean.CommonResultJson;
import com.github.wenbo2018.jconf.web.constants.ResultCode;
import com.github.wenbo2018.jconf.web.dto.User;
import com.github.wenbo2018.jconf.web.service.UserService;
import com.github.wenbo2018.jconf.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenbo.shen on 2017/5/19.
 */
@Controller
@RequestMapping(value = "/jconf/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login/index",method = RequestMethod.GET)
    public String loginIndex() {
        return "user/index";
    }

    /**
     * 登录验证
     * @param userName
     * @param passWord
     * @return
     */
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
        User user = userService.loadUserByUserNameAndPassWord(userName,passWord);

        if (user == null) {
            result.setMessage("用户名或密码错误!");
            result.setCode(ResultCode.PARAMETER_ERROR);
            return result;
        }
        //生产token并保存于cookie中
        Map<String , Object> payload=new HashMap<String, Object>();
        Date date=new Date();
        payload.put("uid", user.getUserId());//用户ID
        payload.put("iat", date.getTime());//生成时间
        payload.put("ext",date.getTime()+1800);//过期时间1小时
        String _token = WebUtils.produceToken(payload);
        WebUtils.addStringToCookie(response,"token",_token,1800);
        result.setMessage("登录成功!");
        return result;
    }
    /**
     * 注册验证
     * @param userName
     * @param passWord
     * @param email
     * @return
     */
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
        int userId = userService.addUser(user);
        //生产token并保存于cookie中
        Map<String , Object> payload=new HashMap<String, Object>();
        Date date=new Date();
        payload.put("uid", userId);//用户ID
        payload.put("iat", date.getTime());//生成时间
        payload.put("ext",date.getTime()+1800);//过期时间1小时
        String _token = WebUtils.produceToken(payload);
        WebUtils.addStringToCookie(response,"token",_token,1800);
        result.setMessage("注册成功!");
        return result;
    }
}
