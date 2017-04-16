package com.github.webbo2018.jconf.web;

import com.github.webbo2018.jconf.core.CuratorClient;
import com.github.webbo2018.jconf.core.registry.CuratorRegistry;
import com.github.webbo2018.jconf.core.registry.Registry;
import com.github.wenbo2018.jconf.common.config.ConfigManager;
import com.github.wenbo2018.jconf.common.config.ConfigManagerLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class RegistryManager {

    private static Logger logger = LoggerFactory.getLogger(RegistryManager.class);
    private static RegistryManager instance = new RegistryManager();
    private static volatile boolean isInit = false;
    private static ConfigManager configManager;
    private static List<Registry> registryList = new ArrayList<>();
    private static String ZOOKEEPER_REGISTER_ADDRESS = "zookeeper.register.address";
    private static final Random random = new Random();

    public static RegistryManager getInstance() {
        if (!isInit) {
            synchronized (RegistryManager.class) {
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
        String addressSplits = configManager.getStringValue(ZOOKEEPER_REGISTER_ADDRESS);
        String[] addressArray = addressSplits.trim().split(",");
        if (addressArray.length <= 0) {
            logger.error("zk client address is not found");
        } else {
            for (int i = 0; i < addressArray.length; i++) {
                String address = addressArray[i];
                Registry registry = new CuratorRegistry();
                registry.init(address);
                registryList.add(registry);
            }
        }
    }
    public static void registerService(String key,String value) {
        if (registryList.size()<1) {
            logger.error("not found zk client");
            return;
        }
        int total = registryList.size();
        int offset = random.nextInt(total);
        registryList.get(offset).registryConfig(key,value);
    }

    public static void unregisterService(String key) {
        if (registryList.size()<1) {
            logger.error("not found zk client");
            return;
        }
        int total = registryList.size();
        int offset = random.nextInt(total);
        registryList.get(offset).unregistryConfig(key);
    }
}
