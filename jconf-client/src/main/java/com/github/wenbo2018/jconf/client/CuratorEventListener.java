package com.github.wenbo2018.jconf.client;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
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


    public CuratorEventListener(CuratorClient client) {
        this.client = client;
    }

    @Override
    public void eventReceived(CuratorFramework client, CuratorEvent curatorEvent) throws Exception {
        WatchedEvent event = (curatorEvent == null ? null : curatorEvent.getWatchedEvent());
        if (event == null || (event.getType() != Watcher.Event.EventType.NodeCreated && event.getType()
                != Watcher.Event.EventType.NodeDataChanged
                && event.getType() != Watcher.Event.EventType.NodeDeleted && event.getType()
                != Watcher.Event.EventType.NodeChildrenChanged)) {
            return;
        }
        logEvent(event);
    }

    private void logEvent(WatchedEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("zookeeper event received, type: ").append(event.getType()).append(", path: ")
                .append(event.getPath());
        logger.info(sb.toString());
    }

}
