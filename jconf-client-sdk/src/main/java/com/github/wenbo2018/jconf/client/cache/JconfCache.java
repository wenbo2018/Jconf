package com.github.wenbo2018.jconf.client.cache;

import com.github.wenbo2018.jconf.client.JconfConfig;
import com.github.wenbo2018.jconf.client.constants.Constants;
import com.github.wenbo2018.jconf.client.exception.JconfException;
import com.github.wenbo2018.jconf.common.extension.ExtensionLoader;
import com.github.wenbo2018.jconf.client.listener.ConfigChangeEvent;
import com.github.wenbo2018.jconf.client.listener.JconfigEventListener;
import com.github.wenbo2018.jconf.client.listener.api.ConfigInfoChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class JconfCache {

    private static Logger logger = LoggerFactory.getLogger(JconfCache.class);
    private static ConcurrentMap<String, String> jconfCacheMap = new ConcurrentHashMap<>();
    private static JconfConfig jconfConfig;
    private static volatile boolean isInit = false;
    private static JconfCache instance = new JconfCache();

    private ConfigInfoChangeListener configInfoChangeListener = new InnerJconfCacheConfigChangeListener();

    public static JconfCache getInstance() {
        if (!isInit) {
            synchronized (JconfCache.class) {
                if (!isInit) {
                    instance.init();
                    instance.sync();
                    isInit = true;
                }
            }
        }
        return instance;
    }

    private void init() {
        this.jconfConfig = ExtensionLoader.getExtension(JconfConfig.class);
        JconfigEventListener.addListener(configInfoChangeListener);
    }

    private void sync() {
        jconfCacheMap=jconfConfig.sync();
        logger.info("jconf config data sync success");
    }

    public String getValue(String key) {
        if (key == null) {
            throw new JconfException("jconf key is null");
        }
        String value = null;
        value = jconfCacheMap.get(key);
        if (value == null) {
            value = jconfConfig.getValue(key);
            if (value == null) {
                logger.warn("value is null from zk config center{}:", key);
            } else {
                jconfCacheMap.put(key, value);
            }
        }
        return value;
    }

    public ConcurrentMap<String, String> getJconfCache() {
        return jconfCacheMap;
    }

    private class InnerJconfCacheConfigChangeListener implements ConfigInfoChangeListener {

        @Override
        public void configChange(ConfigChangeEvent configChangeEvent) {
            if (configChangeEvent.getEventType() == 0) {
                return;
            } else if (configChangeEvent.getEventType() == Constants.CONFIG_CREATE) {
                String key = configChangeEvent.getKey();
                String value = configChangeEvent.getValue();
                jconfCacheMap.put(key, value);
                logger.info("config create:{}", configChangeEvent.toString());
            } else if (configChangeEvent.getEventType() == Constants.CONFIG_DELETE) {
                jconfCacheMap.remove(configChangeEvent.getKey());
                logger.info("config delete:{}", configChangeEvent.toString());
            } else if (configChangeEvent.getEventType() == Constants.CONFIG_UPDATE) {
                String key = configChangeEvent.getKey();
                String value = configChangeEvent.getValue();
                jconfCacheMap.put(key, value);
                logger.info("config update:{}", configChangeEvent.toString());
            } else {
                return;
            }
        }
    }
}
