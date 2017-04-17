package com.github.wenbo2018.jconf.core.registry;
import com.github.wenbo2018.jconf.core.CuratorClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class CuratorRegistry implements Registry {

    private Logger logger = LoggerFactory.getLogger(CuratorRegistry.class);
    private CuratorClient curatorClient;
    private static final String JCONF_PREFIX = "/JCONF/CONFIG";
    private static volatile boolean isInitialized=false;
    @Override
    public void init(String adress) {
        if (!isInitialized) {
            synchronized (this) {
                if (!isInitialized) {
                    try {
                        if (adress != null) {
                            logger.info("start to initialize zookeeper client:",adress);
                            curatorClient = new CuratorClient(adress);
                            logger.info("succeed to initialize zookeeper client:",adress);
                            isInitialized = true;
                        } else {
                            logger.error("zookeeper server adress is null");
                        }
                    } catch (Exception ex) {
                        logger.error("failed to initialize zookeeper client", ex);
                    }
                }
            }
        }
    }

    @Override
    public void registryConfig(String key, String value) {
        try {
            if (!curatorClient.exists(JCONF_PREFIX)) {
                curatorClient.creatrPersistentNode(JCONF_PREFIX);
            }
            curatorClient.set(JCONF_PREFIX + "/" + key, value);
        } catch (Exception e) {
            logger.error("config register fail{}:", key, value);
        }
    }

    @Override
    public void unregistryConfig(String key) {
        try {
            curatorClient.delete(key);
        } catch (Exception e) {
            logger.error("unregistry config error", e);
        }
    }

    @Override
    public String get(String key) {
        String value=null;
        try {
            value=curatorClient.get(JCONF_PREFIX + "/" + key);
        } catch (Exception e) {
            logger.error("get config error", e);
        }
        return value;
    }
}
