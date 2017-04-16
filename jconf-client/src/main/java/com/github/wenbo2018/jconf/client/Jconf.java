package com.github.wenbo2018.jconf.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wenbo.shen on 2017/4/15.
 */
public class Jconf {

    private static Logger logger= LoggerFactory.getLogger(Jconf.class);
    private static Jconf instance;
    private static volatile boolean init=false;
    private Jconf getInstance() {
        if (!init) {
            if (!init) {
                synchronized (this) {
                    instance = new Jconf();
                }
            }
        }
        return instance;
    }
}
