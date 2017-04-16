package com.github.webbo2018.jconf.core;

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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shenwenbo on 2017/4/16.
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

    /**
     * 初始化zk客户端
     */
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
                logger.info("zookeeper state changed to " + newState);
                if (newState == ConnectionState.RECONNECTED) {
//                    RegistryEventListener.connectionReconnected();
                }
            }
        });
        client.start();
        boolean isConnected = client.getZookeeperClient().blockUntilConnectedOrTimedOut();
        CuratorFramework oldClient = this.zookeeperClient;
        this.zookeeperClient = client;
        close(oldClient);
        logger.info("succeed to create zookeeper client, connected:" + isConnected);
        return isConnected;
    }

    public CuratorFramework getZookeeperClient() {
        return zookeeperClient;
    }


    public String get(String path) throws Exception {
        return get(path, true);
    }


    public String get(String path, Stat stat) throws Exception {
        if (exists(path, false)) {
            byte[] bytes = zookeeperClient.getData().storingStatIn(stat).forPath(path);
            String value = new String(bytes, CHARSET);
            logger.debug("get value of node " + path + ", value " + value);
            return value;
        } else {
            logger.debug("node " + path + " does not exist");
            return null;
        }
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


    public void set(String path, Object value) throws Exception {
        byte[] bytes = (value == null ? new byte[0] : value.toString().getBytes(CHARSET));
        if (exists(path, false)) {
            zookeeperClient.setData().forPath(path, bytes);
            logger.debug("set value of node " + path + " to " + value);
        } else {
            zookeeperClient.create().creatingParentsIfNeeded().forPath(path, bytes);
            logger.debug("create node " + path + " value " + value);
        }
    }


    public void create(String node, String value) throws Exception {
        byte[] bytes = (value == null ? new byte[0] : value.toString().getBytes(CHARSET));
        String addressNode = zookeeperClient.create().withMode(CreateMode.EPHEMERAL).forPath(node, bytes);
        logger.info("create address node:", addressNode);
    }


    public void creatrPersistentNode(String path) throws Exception {
        if (!exists(path, false)) {
            zookeeperClient.create().creatingParentsIfNeeded().forPath(path);
            logger.info("create registry node:", path);
        }
    }

    public void delete(String path) throws Exception {
        zookeeperClient.delete().forPath(path);
        if (logger.isInfoEnabled()) {
            logger.info("delete node " + path);
        }
    }


    public void deleteIfExists(String path) throws Exception {
        if (exists(path, false)) {
            delete(path);
        } else {
            logger.warn("node " + path + " not exists!");
        }
    }


    public void watch(String path) throws Exception {
        zookeeperClient.checkExists().watched().forPath(path);
    }

    private void close(CuratorFramework client) {
        if (client != null) {
            logger.info("begin to close zookeeper client");
            try {
                client.close();
                logger.info("succeed to close zookeeper client");
            } catch (Exception e) {
            }
        }
    }


    public boolean exists(String path) throws Exception {
        Stat stat = zookeeperClient.checkExists().watched().forPath(path);
        return stat != null;
    }


    public boolean exists(String path, boolean watch) throws Exception {
        Stat stat = watch ? zookeeperClient.checkExists().watched().
                forPath(path) : zookeeperClient.checkExists().forPath(path);
        return stat != null;
    }

}
