package com.github.wenbo2018.web.test;

import com.github.webbo2018.jconf.web.Application;
import com.github.webbo2018.jconf.web.RegistryManager;
import com.github.webbo2018.jconf.web.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by shenwenbo on 2017/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringApplicationConfiguration(classes =Application.class) // 指定我们SpringBoot工程的
public class ConfigServiceTest {

    @Resource
    ConfigService configService;
    @Test
    public void add() {
        RegistryManager.getInstance();
    }
}
