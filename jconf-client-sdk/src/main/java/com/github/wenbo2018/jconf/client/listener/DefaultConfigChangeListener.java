package com.github.wenbo2018.jconf.client.listener;

import com.github.wenbo2018.jconf.client.listener.api.ConfigChangeListener;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class DefaultConfigChangeListener implements ConfigChangeListener {

    @Override
    public void onChange(ConfigChangeEvent event) {
        JconfigEventListener.configChange(event);
    }

}
