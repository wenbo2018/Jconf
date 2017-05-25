package com.github.wenbo2018.jconf.client.listener;

import com.github.wenbo2018.jconf.client.listener.api.ConfigInfoChangeListener;
import com.github.wenbo2018.jconf.client.listener.api.CuratorClientConnectionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class JconfigEventListener {

    private static List<ConfigInfoChangeListener> configInfoChangeListeners=new ArrayList<ConfigInfoChangeListener>();

    private static List<CuratorClientConnectionListener> curatorClientConnectionListenerList=new ArrayList<CuratorClientConnectionListener>();

    public static void addListener(ConfigInfoChangeListener configInfoChangeListener){
        configInfoChangeListeners.add(configInfoChangeListener);
    }

    public static void addListener(CuratorClientConnectionListener curatorClientConnectionListener) {
        curatorClientConnectionListenerList.add(curatorClientConnectionListener);
    }

    public static void configChange(ConfigChangeEvent event) {
        for (ConfigInfoChangeListener config:configInfoChangeListeners) {
            config.configChange(event);
        }
    }

    public static void curatorClientConnection() {
        for (CuratorClientConnectionListener curatorClientConnectionListener:curatorClientConnectionListenerList) {
            curatorClientConnectionListener.reconnected();
        }
    }

}
