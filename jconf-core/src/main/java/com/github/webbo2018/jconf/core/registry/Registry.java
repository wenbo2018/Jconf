package com.github.webbo2018.jconf.core.registry;

import java.util.Properties;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public interface Registry {

    void init(String adress);

    void registryConfig(String key,String value);

    void unregistryConfig(String key);

    String get(String key);

}
