package com.github.wenbo2018.web.test;

import com.github.wenbo2018.jconf.web.Application;
import com.github.wenbo2018.jconf.web.dto.Config;
import com.github.wenbo2018.jconf.web.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by shenwenbo on 2017/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringApplicationConfiguration(classes = Application.class) // 指定我们SpringBoot工程的
public class ConfigServiceTest {

    @Autowired
    ConfigService configService;

    @Test
    public void add() {
//        RegistryManager.getInstance();

        for (int i = 1; i < 100; i++) {
            Config config = new Config();
            config.setKey("key"+i);
            config.setValue("wenb2018"+i);
            config.setEnv(i);
            config.setProjectId(i);
            config.setUserName("wenbo"+1);
            config.setUserEmail("1sdf23+"+i+"@qq.com");
            configService.add(config);
        }

//        RegistryManager.registerService("key","spoomjuijhnj");
//        System.out.println(RegistryManager.getService("key"));
    }
}
