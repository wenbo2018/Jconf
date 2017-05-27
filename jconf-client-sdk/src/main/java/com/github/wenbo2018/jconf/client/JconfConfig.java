package com.github.wenbo2018.jconf.client;


import java.util.concurrent.ConcurrentMap;

/**
 * Created by shenwenbo on 2017/4/16.
 */
public interface JconfConfig {

     String getValue(String key);

     ConcurrentMap<String,String> sync();

}
