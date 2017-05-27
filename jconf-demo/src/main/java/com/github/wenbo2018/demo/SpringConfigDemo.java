package com.github.wenbo2018.demo;

import com.github.wenbo2018.demo.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wenbo.shen on 2017/5/25.
 */
public class SpringConfigDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("application.xml");
        User user= (User) applicationContext.getBean("user");
        System.err.println(user.getUserName());
    }
}
