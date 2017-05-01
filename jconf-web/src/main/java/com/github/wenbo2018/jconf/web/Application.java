package com.github.wenbo2018.jconf.web;

import com.github.wenbo2018.jconf.web.listener.ConfigPushListener;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by shenwenbo on 2017/4/14.
 */
@SpringBootApplication
@MapperScan("com.github.wenbo2018.jconf.web.dao")
public class Application {
    private Logger logger= LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ConfigPushListener());
        app.run(args);
    }

}
