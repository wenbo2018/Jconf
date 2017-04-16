package com.github.wenbo2018.jconf.client.config;

import java.util.Properties;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public interface ConfigManager {
    public void init();

    public void init(Properties properties);

    public Properties getRegistryConfig();

    public String getStringValue(String key);
}
