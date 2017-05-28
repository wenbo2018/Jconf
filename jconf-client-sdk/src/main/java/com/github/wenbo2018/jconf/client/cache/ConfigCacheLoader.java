package com.github.wenbo2018.jconf.client.cache;

import com.github.wenbo2018.jconf.common.extension.ExtensionLoader;
import org.apache.log4j.Logger;

/**
 * Created by wenbo.shen on 2017/5/28.
 */
public class ConfigCacheLoader {

    private static final Logger logger = Logger.getLogger(ConfigCacheLoader.class);
    private static ConfigCache configCache = ExtensionLoader.getExtension(ConfigCache.class);

    public static void init(){
        if (configCache == null) {
            configCache = new DefaultConfigCache();
        }
        logger.debug("configCache init");
        configCache.init();
    }

    public static ConfigCache getConfigCache() {
        if (configCache == null) {
            configCache = new DefaultConfigCache();
        }
        if (configCache.isInit()) {
            return configCache;
        }
        configCache.init();
        return configCache;
    }
}
