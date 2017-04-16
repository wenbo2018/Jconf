package com.github.wenbo2018.jconf.client.listener;

import com.github.wenbo2018.jconf.client.listener.api.ConfigInfoChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class JconfEventListener {

    private static List<ConfigInfoChangeListener> configInfoChangeListeners=new ArrayList<>();

    public static void addListener(ConfigInfoChangeListener configInfoChangeListener){
        configInfoChangeListeners.add(configInfoChangeListener);
    }

    public static void configChange(ConfigChangeEvent event) {
        for (ConfigInfoChangeListener config:configInfoChangeListeners) {
            config.configChange(event);
        }
    }


}
