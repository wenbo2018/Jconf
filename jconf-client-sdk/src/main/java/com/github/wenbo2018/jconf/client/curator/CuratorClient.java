package com.github.wenbo2018.jconf.client.curator;

import com.github.wenbo2018.jconf.common.pool.DefaultThreadFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shenwenbo on 2016/10/17.
 */
public class CuratorClient {

    private static Logger logger = LoggerFactory.getLogger(CuratorClient.class);

    private String address;

    private static final String CHARSET = "UTF-8";

    private CuratorFramework zookeeperClient;

    private static ExecutorService curatorEventListenerThreadPool = Executors
            .newCachedThreadPool( new DefaultThreadFactory("Jconf-Curator-Event-Listener"));

    public CuratorClient(String zkAddress) throws InterruptedException {
        this.address = zkAddress;
        newZkClient();
    }

    public boolean newZkClient() throws InterruptedException {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .ensembleProvider(new DefaultEnsembleProvider(address))
                .sessionTimeoutMs(30 * 1000)
                .connectionTimeoutMs(15 * 1000)
                .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
                .build();
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                if (newState==ConnectionState.RECONNECTED) {
                    logger.info("zookeeper state changed to:{}",newState);
                }
                if (newState == ConnectionState.RECONNECTED) {
                    logger.info("zookeeper state changed to:{}",newState);
                }
            }
        });
        //事件监听;
        client.getCuratorListenable().addListener(new CuratorEventListener(this), curatorEventListenerThreadPool);
        client.start();
        boolean isConnected = client.getZookeeperClient().blockUntilConnectedOrTimedOut();
        CuratorFramework oldClient = this.zookeeperClient;
        this.zookeeperClient = client;
        close(oldClient);
        logger.info("succeed to create zookeeper curator, connected:" + isConnected);
        return isConnected;
    }

    public CuratorFramework getZookeeperClient() {
        return zookeeperClient;
    }

    public List<String> getChild(String path) throws Exception {
        if (!exists(path)) {
            throw new RuntimeException(String.format("can not find any service node on path: %s", path));
        }
        List<String> addressList = null;
        try {
            addressList = zookeeperClient.getChildren().watched().forPath(path);
        } catch (Exception e) {
            logger.debug("node " + path + " does not exist");
            return null;
        }
        return addressList;
    }

    public String get(String path) throws Exception {
        return get(path, true);
    }

    public String get(String path, boolean watch) throws Exception {
        if (exists(path, watch)) {
            byte[] bytes = zookeeperClient.getData().forPath(path);
            String value = new String(bytes, CHARSET);
            logger.debug("get value of node " + path + ", value " + value);
            return value;
        } else {
            logger.debug("node " + path + " does not exist");
            return null;
        }
    }

    public void delete(String path) throws Exception {
        zookeeperClient.delete().forPath(path);
        if (logger.isInfoEnabled()) {
            logger.info("delete node " + path);
        }
    }

    private void close(CuratorFramework client) {
        if (client != null) {
            logger.info("begin to close zookeeper curator");
            try {
                client.close();
                logger.info("succeed to close zookeeper curator");
            } catch (Exception e) {
            }
        }
    }

    public boolean exists(String path) throws Exception {
        Stat stat = zookeeperClient.checkExists().watched().forPath(path);
        return stat != null;
    }

    public boolean exists(String path, boolean watch) throws Exception {
        Stat stat = watch ? zookeeperClient.checkExists().watched().forPath(path) : zookeeperClient.checkExists().forPath(path);
        return stat != null;
    }

}
