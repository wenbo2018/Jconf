package com.github.wenbo2018.jconf.web.web;

import com.github.wenbo2018.jconf.web.constants.Constants;
import com.github.wenbo2018.jconf.web.dto.User;
import com.github.wenbo2018.jconf.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wenbo.shen on 2017/5/28.
 */
public abstract class AbstractController {

    private static Logger logger = LoggerFactory.getLogger(AbstractController.class);

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected UserService userService;

    protected int getUserId() {
        Object o = request.getAttribute(Constants.USER_ID);
        int userId= o == null ? 0 : (Integer) o;
        return userId;
    }

    protected User getUser() {
        Object o = request.getAttribute(Constants.USER_ID);
        int userId= o == null ? 0 : (Integer) o;
        if (userId<=0) {
            return null;
        }
        User user = userService.loadUserByUserId(userId);
        return user;
    }

}
