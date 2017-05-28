package com.github.wenbo2018.jconf.web.listener;

import com.github.wenbo2018.jconf.web.cuator.RegistryManager;
import com.github.wenbo2018.jconf.web.dao.ConfigDao;
import com.github.wenbo2018.jconf.web.dto.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wenbo.shen on 2017/5/1.
 */
public class ConfigPushListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(ApplicationStartedEvent.class);

    private ConfigDao configDao;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Jconf webapp started");
        RegistryManager.getInstance();
        configDao=event.getApplicationContext().getBean(ConfigDao.class);
        List<Config> configList = configDao.list();
        if (!CollectionUtils.isEmpty(configList)) {
            for (Config config : configList) {
                String value = RegistryManager.getService(config.getKey());
                if (value == null) {
                    RegistryManager.registerService(config.getKey(), config.getValue());
                    logger.info("push config to jconf:{}", config.toString());
                }
            }
        }
        logger.info("config push success!");
    }
}
