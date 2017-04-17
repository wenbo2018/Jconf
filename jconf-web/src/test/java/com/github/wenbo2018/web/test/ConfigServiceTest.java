package com.github.wenbo2018.web.test;

import com.github.wenbo2018.jconf.web.Application;
import com.github.wenbo2018.jconf.web.RegistryManager;
import com.github.wenbo2018.jconf.web.dto.Config;
import com.github.wenbo2018.jconf.web.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by shenwenbo on 2017/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringApplicationConfiguration(classes =Application.class) // 指定我们SpringBoot工程的
public class ConfigServiceTest {

//    @Autowired
//    ConfigService configService;
    @Test
    public void add() {
        RegistryManager.getInstance();
//        Config config=new Config();
//        config.setKey("key");
//        config.setValue("wenbo2018");
//        config.setEnv(1);
//        config.setProjectId(12);
//        config.setUserName("wenbo");
//        config.setUserEmail("123@qq.com");
//
//        configService.add(config);
        RegistryManager.registerService("key","spoomjuijhnj");
        System.out.println(RegistryManager.getService("key"));
    }
}
