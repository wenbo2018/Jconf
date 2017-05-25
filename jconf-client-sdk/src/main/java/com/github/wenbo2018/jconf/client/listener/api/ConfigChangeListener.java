package com.github.wenbo2018.jconf.client.listener.api;

import com.github.wenbo2018.jconf.client.listener.ConfigChangeEvent;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public interface ConfigChangeListener {

    void onChange(ConfigChangeEvent event);

}
