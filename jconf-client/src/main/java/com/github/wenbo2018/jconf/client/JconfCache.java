package com.github.wenbo2018.jconf.client;

import com.github.wenbo2018.jconf.client.exception.JconfException;
import com.github.wenbo2018.jconf.client.extension.ExtensionLoader;
import com.github.wenbo2018.jconf.client.listener.ConfigChangeEvent;
import com.github.wenbo2018.jconf.client.listener.JconfEventListener;
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
    private static ConcurrentMap<String, String> jconfCache = new ConcurrentHashMap<>();
    private static JconfConfig jconfConfig;
    private static volatile boolean isInit = false;
    private static JconfCache instance = new JconfCache();

    private ConfigInfoChangeListener configInfoChangeListener = new InnerJconfCacheConfigChangeListener();

    public static JconfCache getInstance() {
        if (!isInit) {
            synchronized (JconfCache.class) {
                if (!isInit) {
                    instance.init();
                    isInit = true;
                }
            }
        }
        return instance;
    }

    private void init() {
        this.jconfConfig = ExtensionLoader.getExtension(JconfConfig.class);
        JconfEventListener.addListener(configInfoChangeListener);
    }

    String getValue(String key) {
        if (key == null) {
            throw new JconfException("jconf key is null");
        }
        String value = null;
        value = jconfCache.get(key);
        if (value == null) {
            value = jconfConfig.getValue(key);
            if (value == null) {
                logger.warn("value is null from zk config center{}:", key);
            } else {
                jconfCache.put(key, value);
            }
        }
        return value;
    }

    class InnerJconfCacheConfigChangeListener implements ConfigInfoChangeListener {

        @Override
        public void configChange(ConfigChangeEvent configChangeEvent) {
            String key = configChangeEvent.getKey();
            String value = configChangeEvent.getValue();
            if (!jconfCache.containsKey(key)) {
                jconfCache.put(key,value);
            }
            jconfCache.put(key,value);
        }
    }

}
