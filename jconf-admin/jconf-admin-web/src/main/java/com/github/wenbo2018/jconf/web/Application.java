package com.github.wenbo2018.jconf.web;

import com.github.wenbo2018.jconf.web.listener.ConfigPushListener;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created by shenwenbo on 2017/4/14.
 */
@SpringBootApplication
@MapperScan("com.github.wenbo2018.jconf.dao")
@ServletComponentScan
@ComponentScan("com.github.wenbo2018")
public class Application {
    private static Logger logger= LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ConfigPushListener());
        app.run(args);
        logger.info("Jconf Application started");
    }
}
