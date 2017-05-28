package com.github.wenbo2018.jconf.client.cache;

import java.util.Map;

/**
 * Created by wenbo.shen on 2017/5/28.
 */
public interface ConfigCache {
    boolean isInit();
    void init();
    String getString(String key);
    void sysc();
}
