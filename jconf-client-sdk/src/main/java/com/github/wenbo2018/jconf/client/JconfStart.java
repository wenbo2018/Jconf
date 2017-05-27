package com.github.wenbo2018.jconf.client;

import com.github.wenbo2018.jconf.client.cache.JconfCache;
import com.github.wenbo2018.jconf.client.curator.CuratorManager;
import com.github.wenbo2018.jconf.common.config.ConfigManagerLoader;

/**
 * Created by wenbo.shen on 2017/5/25.
 */
public class JconfStart {

    static {
        ConfigManagerLoader.init();
        CuratorManager.getInstance();
        JconfCache.getInstance();
    }

}
