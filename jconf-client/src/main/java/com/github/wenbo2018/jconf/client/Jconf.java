package com.github.wenbo2018.jconf.client;

import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wenbo.shen on 2017/4/15.
 */
public class Jconf {

    private static Logger logger = LoggerFactory.getLogger(Jconf.class);
    private static Jconf instance;
    private static volatile boolean isInit = false;
    private static JconfCache jconfCache;

    public static Jconf getInstance() {
        if (!isInit) {
            synchronized (Jconf.class) {
                if (!isInit) {
                    instance = new Jconf();
                    init();
                }
            }
        }
        return instance;
    }

    private static void init() {
        jconfCache = JconfCache.getInstance();
    }

    public static String getString(String key) {
        String value = Jconf.getInstance().jconfCache.getValue(key);
        return value;
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 10000;
                while (i > 0) {
                    System.out.println(Jconf.getString("key"));
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
