package com.github.wenbo2018.jconf.client.curator;


import com.github.wenbo2018.jconf.client.bean.ConfigInfo;
import com.github.wenbo2018.jconf.client.constants.Constants;
import com.github.wenbo2018.jconf.client.listener.ConfigChangeEvent;
import com.github.wenbo2018.jconf.client.listener.api.ConfigChangeListener;
import com.github.wenbo2018.jconf.client.listener.DefaultConfigChangeListener;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by shenwenbo on 2017/3/11.
 */
public class CuratorEventListener implements CuratorListener {

    private Logger logger = LoggerFactory.getLogger(CuratorEventListener.class);
    private CuratorClient client;
    private ConfigChangeListener configChangeListener = new DefaultConfigChangeListener();

    public CuratorEventListener(CuratorClient client) {
        this.client = client;
    }

    @Override
    public void eventReceived(CuratorFramework client, CuratorEvent curatorEvent) throws Exception {
        if (curatorEvent != null && curatorEvent.getType() == CuratorEventType.WATCHED) {
            WatchedEvent watchedEvent = curatorEvent.getWatchedEvent();
            if (watchedEvent.getPath() != null) {
                try {
                    logEvent(watchedEvent);
                    processEvent(watchedEvent);
                } catch (Exception e) {
                    logger.error("curator event process fail",e);
                }
            }
        }

    }

    private void processEvent(WatchedEvent watchedEvent) {
        String path = watchedEvent.getPath();
        ConfigInfo configInfo=parseConfig(path);
        int eventType=0;
        if (watchedEvent.getType().getIntValue()== Watcher.Event.EventType.None.getIntValue()) {
            return;
        } else if (watchedEvent.getType().getIntValue()== Watcher.Event.EventType.NodeCreated.getIntValue()) {
            eventType=Constants.CONFIG_CREATE;
        } else if (watchedEvent.getType().getIntValue()== Watcher.Event.EventType.NodeDataChanged.getIntValue()) {
            eventType=Constants.CONFIG_UPDATE;
        } else if (watchedEvent.getType().getIntValue()== Watcher.Event.EventType.NodeDeleted.getIntValue()) {
            eventType=Constants.CONFIG_DELETE;
        } else {
            return;
        }
        ConfigChangeEvent configChangeEvent=new ConfigChangeEvent();
        configChangeEvent.setKey(configInfo.getKey());
        configChangeEvent.setValue(configInfo.getValue());
        configChangeEvent.setEventType(eventType);
        configChangeListener.onChange(configChangeEvent);
    }

    private void logEvent(WatchedEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("zookeeper event received, type: ").append(event.getType()).append(", path: ").append(event.getPath());
        logger.info(sb.toString());
    }


    public ConfigInfo parseConfig(String path){
        if (path == null||!path.startsWith(Constants.JCONF_PREFEX)) {
            return null;
        }
        String key=path.substring(Constants.JCONF_PREFEX.length()+1);
        String value=null;
        try {
            value = client.get(path);
        } catch (Exception e) {
            logger.error("get config value error",e);
        }
        ConfigInfo configInfo=new ConfigInfo();
        configInfo.setKey(key);
        configInfo.setValue(value);
        return configInfo;
    }
}
