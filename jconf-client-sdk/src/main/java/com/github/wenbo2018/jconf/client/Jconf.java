package com.github.wenbo2018.jconf.client;

import com.alibaba.fastjson.JSON;
import com.github.wenbo2018.jconf.client.cache.ConfigCache;
import com.github.wenbo2018.jconf.client.cache.ConfigCacheLoader;
import com.github.wenbo2018.jconf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by wenbo.shen on 2017/4/15.
 */
public class Jconf {

    private static Logger logger = LoggerFactory.getLogger(Jconf.class);
    private static Jconf instance;
    private static volatile boolean isInit = false;
    private static ConfigCache configCache;

    public static Jconf getInstance() {
        if (!isInit) {
            synchronized (Jconf.class) {
                if (!isInit) {
                    instance = new Jconf();
                    init();
                    isInit=true;
                }
            }
        }
        return instance;
    }

    public static void init() {
        if (configCache==null) {
            configCache=ConfigCacheLoader.getConfigCache();
        }
    }

    public static String getString(String key) {
        String value = Jconf.getInstance().configCache.getString(key);
        return value;
    }

    public static double getDouble(String key) {
        String value = Jconf.getInstance().configCache.getString(key);
        return StringUtils.parseDouble(value);
    }

    public static int  getInt(String key) {
        String value = Jconf.getInstance().configCache.getString(key);
        return StringUtils.parseInt(value);
    }

    public static Map<String,String> getMap(String key) {
        String value = Jconf.getInstance().configCache.getString(key);
        JSON json= (JSON) JSON.toJSON(value);
        return JSON.toJavaObject(json,Map.class);
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = Integer.MAX_VALUE;
                while (i > 0) {
                    System.out.println(Jconf.getString("https"));
                    i--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
