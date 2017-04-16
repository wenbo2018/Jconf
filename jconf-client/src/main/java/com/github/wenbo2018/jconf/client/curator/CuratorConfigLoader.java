package com.github.wenbo2018.jconf.client.curator;

import com.github.wenbo2018.jconf.client.JconfConfig;
import com.github.wenbo2018.jconf.client.listener.ConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class CuratorConfigLoader implements JconfConfig {

    private static Logger logger= LoggerFactory.getLogger(CuratorConfigLoader.class);
    private ConfigChangeListener configChangeListener;

    @Override
    public String getValue(String key) {
        String value=null;
        try {
            value=CuratorManager.getInstance().getCuratorClient().get(key);
        } catch (Exception e) {
            logger.error("get zk value error",e);
        }
        return value;
    }

    @Override
    public void addConfigChangeListener(ConfigChangeListener configChangeListener) {
        this.configChangeListener = configChangeListener;
    }

    @Override
    public void removeConfigChangeListener(ConfigChangeListener configChangeListener) {
        this.configChangeListener = null;
    }
}
