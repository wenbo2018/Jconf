package com.github.wenbo2018.jconf.client.listener;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class DefaultConfigChangeListener implements ConfigChangeListener {

    @Override
    public void onChange(ConfigChangeEvent event) {
        JconfEventListener.configChange(event);
    }

}
