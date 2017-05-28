package com.github.wenbo2018.jconf.client.cache;

import com.github.wenbo2018.jconf.client.JconfConfig;
import com.github.wenbo2018.jconf.client.constants.Constants;
import com.github.wenbo2018.jconf.client.exception.JconfException;
import com.github.wenbo2018.jconf.client.listener.ConfigChangeEvent;
import com.github.wenbo2018.jconf.client.listener.JconfigEventListener;
import com.github.wenbo2018.jconf.client.listener.api.ConfigInfoChangeListener;
import com.github.wenbo2018.jconf.common.extension.ExtensionLoader;
import com.github.wenbo2018.jconf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by wenbo.shen on 2017/5/28.
 */
public class AbstractCache implements ConfigCache{

    private static Logger logger = LoggerFactory.getLogger(AbstractCache.class);
    private static ConcurrentMap<String, String> configConcurrentHashMap = new ConcurrentHashMap<>();
    private static JconfConfig jconfConfig;
    private static volatile boolean isInit = false;

    private ConfigInfoChangeListener configInfoChangeListener = new InnerJconfCacheConfigChangeListener();


    @Override
    public boolean isInit() {
        return isInit;
    }

    @Override
    public void init() {
        if (!isInit) {
            synchronized (AbstractCache.class) {
                if (!isInit) {
                    initContext();
                    sysc();
                    isInit = true;
                }
            }
        }
    }

    private void initContext() {
        this.jconfConfig = ExtensionLoader.getExtension(JconfConfig.class);
        JconfigEventListener.addListener(configInfoChangeListener);
    }

    @Override
    public String getString(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new JconfException("jconf key is null");
        }
        String value = null;
        value = configConcurrentHashMap.get(key);
        if (StringUtils.isEmpty(value)) {
            value = jconfConfig.getValue(key);
            if (value == null) {
                logger.warn("value is null from Jconf config center{}:", key);
            } else {
                configConcurrentHashMap.put(key, value);
            }
        }
        return value;
    }

    @Override
    public void sysc() {
        configConcurrentHashMap=jconfConfig.sync();
        logger.info("jconf config data sync success");
    }

    private class InnerJconfCacheConfigChangeListener implements ConfigInfoChangeListener {

        @Override
        public void configChange(ConfigChangeEvent configChangeEvent) {
            if (configChangeEvent.getEventType() == 0) {
                return;
            } else if (configChangeEvent.getEventType() == Constants.CONFIG_CREATE) {
                String key = configChangeEvent.getKey();
                String value = configChangeEvent.getValue();
                configConcurrentHashMap.put(key, value);
                logger.info("config create:{}", configChangeEvent.toString());
            } else if (configChangeEvent.getEventType() == Constants.CONFIG_DELETE) {
                configConcurrentHashMap.remove(configChangeEvent.getKey());
                logger.info("config delete:{}", configChangeEvent.toString());
            } else if (configChangeEvent.getEventType() == Constants.CONFIG_UPDATE) {
                String key = configChangeEvent.getKey();
                String value = configChangeEvent.getValue();
                configConcurrentHashMap.put(key, value);
                logger.info("config update:{}", configChangeEvent.toString());
            } else {
                return;
            }
        }
    }

}
