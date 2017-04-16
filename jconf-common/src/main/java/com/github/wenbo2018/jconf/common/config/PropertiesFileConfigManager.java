package com.github.wenbo2018.jconf.common.config;

import com.github.wenbo2018.jconf.common.util.FileUtils;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class PropertiesFileConfigManager implements ConfigManager {
    private static Logger logger = Logger.getLogger(PropertiesFileConfigManager.class);
    private static final String ENV_FILE_WIN = "C:/data/app/jconf.properties";
    private static final String ENV_FILE_LINUX = "/data/app/jconf.properties";
    private static final String OS_NAME = "os.name";
    private static final String OS_WIN_X86 = "win";
    private static volatile boolean isInit=false;
    private Properties properties;
    Map<String, Object> cacheValue = new ConcurrentHashMap<String, Object>();

    @Override
    public void init() {
        if (!isInit) {
            synchronized (PropertiesFileConfigManager.class) {
                if (!isInit) {
                    try {
                        String os = System.getProperty(OS_NAME);
                        if (os.toLowerCase().startsWith(OS_WIN_X86)) {
                            properties = FileUtils.readFile(new FileInputStream(ENV_FILE_WIN));
                        } else {
                            properties = FileUtils.readFile(new FileInputStream(ENV_FILE_LINUX));
                        }
                        loadProperties(cacheValue, properties);
                    } catch (Throwable e) {
                        logger.error("error when loadProperties:", e);
                    }
                }
                isInit=true;
            }
        }
    }

    @Override
    public void init(Properties properties) {
        this.properties = properties;
        for (Iterator ir = properties.keySet().iterator(); ir.hasNext(); ) {
            String key = ir.next().toString();
            String value = properties.getProperty(key);
            cacheValue.put(key, value);
        }
    }

    @Override
    public boolean isInit() {
        return isInit;
    }

    @Override
    public Properties getRegistryConfig() {
        return this.properties;
    }


    @Override
    public String getStringValue(String key) {
        return (String) cacheValue.get(key);
    }

    public static void loadProperties(Map<String, Object> results, Properties properties) {
        for (Iterator ir = properties.keySet().iterator(); ir.hasNext(); ) {
            String key = ir.next().toString();
            if (key.startsWith("#")) {
                continue;
            }
            String value = properties.getProperty(key);
            value = value.trim();
            results.put(key, value.trim());
        }
    }
}
