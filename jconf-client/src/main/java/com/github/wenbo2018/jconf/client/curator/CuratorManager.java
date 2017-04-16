package com.github.wenbo2018.jconf.client.curator;

import com.github.wenbo2018.jconf.client.config.ConfigManager;
import com.github.wenbo2018.jconf.client.config.ConfigManagerLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class CuratorManager {

    private static Logger logger = LoggerFactory.getLogger(CuratorManager.class);
    private static volatile boolean isInit = false;
    private static CuratorManager instance = new CuratorManager();
    private static ConcurrentMap<String, CuratorClient> curatorClientCache = new ConcurrentHashMap<>();
    private static ConfigManager configManager;
    private static String ZOOKEEPER_REGISTER_ADDRESS="zookeeper.register.address";

    public static CuratorManager getInstance() {
        if (!isInit) {
            synchronized (CuratorManager.class) {
                if (!isInit) {
                    instance.init();
                    isInit = true;
                }
            }
        }
        return instance;
    }

    private void init() {
        this.configManager = ConfigManagerLoader.getConfigManager();
        String addressSplits=configManager.getStringValue(ZOOKEEPER_REGISTER_ADDRESS);
        String [] addressArray=addressSplits.trim().split(",");
        if (addressArray.length<=0) {
            logger.error("zk client address is not found");
        } else {
            for (int i=0;i<addressArray.length;i++) {
                String address=addressArray[i];
                CuratorClient curatorClient=createCuratorClient(address);
                curatorClientCache.put(address,curatorClient);
            }
        }
    }

    private CuratorClient createCuratorClient(String address) {
        CuratorClient curatorClient=null;
        try {
            curatorClient=new CuratorClient(address);
            logger.info("zk client success create{}:",address);
        } catch (InterruptedException e) {
            logger.error("create curator client error",e);
        }
        return curatorClient;
    }
}
