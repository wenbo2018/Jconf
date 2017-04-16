package com.github.wenbo2018.jconf.client;

import com.github.wenbo2018.jconf.client.exception.JconfException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public class JconfCache {
    
    private static Logger logger = LoggerFactory.getLogger(JconfCache.class);
    private static ConcurrentMap<String, String> jconfCache = new ConcurrentHashMap<>();


    String getValue(String key) {
        if (key == null) {
            throw new JconfException("jconf key is null");
        }
        String value=null;
        value=jconfCache.get(key);
        if(value==null) {

        }
    }

}
