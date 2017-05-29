package com.github.wenbo2018.web.test;

import com.github.wenbo2018.jconf.web.dto.User;
import com.github.wenbo2018.jconf.web.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wenbo.shen on 2017/5/28.
 */
public class UserServiceTest extends AbstractTest {
    @Autowired
    UserService userService;

//    @Test
//    public void test_addUser_1() {
//        User user=new User();
//        user.setPassWord("33");
//        user.setUserName("wenbo");
//        user.setToken("12weffdrgere");
//        user.setUserId(12344);
//        user.setUserEmail("51@qq.com");
//        int id=userService.addUser(user);
//        Assert.assertTrue(id>=0);
//    }

    @Test
    public void test_loadUserByUserId_2() {
        User user=userService.loadUserByUserId(12344);
        Assert.assertTrue(user!=null);
    }

    @Test
    public void  test_loadUserByUserNameAndPassWord_3() {
        User user=userService.loadUserByUserNameAndPassWord("wenbo","33");
        Assert.assertTrue(user!=null);
    }
    @Test
    public void loadUserByUserName() {
        User user=userService.loadUserByUserName("wenbo");
        Assert.assertTrue(user!=null);

    }

    @Test
    public void  loadUserByToken() {
        User user=userService.loadUserByToken("12weffdrgere");
        Assert.assertTrue(user!=null);

    }

    @Test
    public void updateUserTokenByUserId() {
        int id=userService.updateUserTokenByUserId("dfgdfgdfgd",12344);
        Assert.assertTrue(id>=0);
    }

}
