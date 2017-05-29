package com.github.wenbo2018.jconf.web.filter;

import com.alibaba.fastjson.JSON;
import com.github.wenbo2018.jconf.common.util.StringUtils;
import com.github.wenbo2018.jconf.web.bean.CommonResultJson;
import com.github.wenbo2018.jconf.web.constants.Constants;
import com.github.wenbo2018.jconf.web.constants.ResultCode;
import com.github.wenbo2018.jconf.web.dto.User;
import com.github.wenbo2018.jconf.web.service.UserService;
import com.github.wenbo2018.jconf.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by wenbo.shen on 2017/5/27.
 */
@WebFilter(filterName = "accountValidateFilter", urlPatterns = "/jconf/*")
public class AccountValidateFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        StringBuffer requestPath=request.getRequestURL();
        boolean isPass=false;
        if (requestPath.toString().contains("login")) {
            isPass=true;
            filterChain.doFilter(servletRequest,servletResponse);
        }
        String token = WebUtils.getStringFromCookie("token", request);
        if (!StringUtils.isEmpty(token)) {
            User user=userService.loadUserByToken(token);
            if (user!=null) {
                isPass=true;
                request.setAttribute(Constants.USER_ID, String.valueOf(user.getUserId()));
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
        if (!isPass) {
            response.sendRedirect("/jconf/user/login/index");
        }
    }

    @Override
    public void destroy() {

    }
}
