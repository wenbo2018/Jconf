package com.github.wenbo2018.jconf.client;

import com.github.wenbo2018.jconf.client.cache.ConfigCacheLoader;
import com.github.wenbo2018.jconf.client.curator.CuratorManager;
import com.github.wenbo2018.jconf.common.config.ConfigManagerLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wenbo.shen on 2017/5/25.
 */
public class ConfigApplication {

    private static Logger logger = LoggerFactory.getLogger(ConfigApplication.class);
    private static volatile boolean isInit = false;

    static {
        if (!isInit) {
                if (!isInit) {
                    ConfigManagerLoader.init();
                    CuratorManager.getInstance();
                    ConfigCacheLoader.init();
                    Jconf.getInstance();
                    isInit = true;
                    logger.info("ConfigApplication started succcess");
                }
        }
    }

}
