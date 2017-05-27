package com.github.wenbo2018.jconf.web.filter;

import com.alibaba.fastjson.JSON;
import com.github.wenbo2018.jconf.common.util.StringUtils;
import com.github.wenbo2018.jconf.web.bean.CommonResultJson;
import com.github.wenbo2018.jconf.web.constants.ResultCode;
import com.github.wenbo2018.jconf.web.dto.User;
import com.github.wenbo2018.jconf.web.service.UserService;
import com.github.wenbo2018.jconf.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenbo.shen on 2017/5/27.
 */
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
        String token = WebUtils.getStringFromCookie("token", request);
        if (StringUtils.isEmpty(token)) {
            String userName = request.getParameter("userName");
            String passWord = request.getParameter("passWord");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            CommonResultJson result = new CommonResultJson();
            result.setCode(ResultCode.SUCCESS);
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
                result.setCode(ResultCode.PARAMETER_ERROR);
                result.setMessage("用户名或密码不能为空!");
            } else {
                if (valideUser(userName, passWord)) {
                    User user=userService.loadUserByUserName(userName);
                    int userId=0;
                    if (user!=null) {
                        userId=user.getId();
                    }
                    //生产token并保存于cookie中
                    Map<String , Object> payload=new HashMap<String, Object>();
                    Date date=new Date();
                    payload.put("uid", userId);//用户ID
                    payload.put("iat", date.getTime());//生成时间
                    payload.put("ext",date.getTime()+1800);//过期时间1小时
                    String _token = WebUtils.produceToken(payload);
                    WebUtils.addStringToCookie(response,"token",_token,1800);
                    result.setMessage("登录成功!");
                } else {
                    result.setCode(ResultCode.PARAMETER_ERROR);
                    result.setMessage("用户名或密码错误!");
                }
            }
            out = response.getWriter();
            out.append(JSON.toJSONString(result));
        } else {
            //利用token进行验证用户


        }
    }

    @Override
    public void destroy() {

    }

    private boolean valideUser(String userName, String password) {
        User user = userService.loadUserByUserNameAndPassWord(userName, password);
        if (user == null) {
            return false;
        }
        return true;
    }

}
