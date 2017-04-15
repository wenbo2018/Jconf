package com.github.wenbo2018.jconf.client.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wenbo2018 on 2017/4/15.
 */
public class DefaultThreadFactory implements ThreadFactory {

    static final AtomicInteger poolNumber = new AtomicInteger(1);
    final AtomicInteger threadNumber;
    final ThreadGroup group;
    final String namePrefix;
    boolean isDaemon = true;//默认为守护线程;


    public DefaultThreadFactory() {
        this("Jconf-default-Pool");
    }

    public DefaultThreadFactory(String name) {
        this(name, true);
    }

    public DefaultThreadFactory(String preffix, boolean daemon) {
        this.threadNumber = new AtomicInteger(1);

        this.group = new ThreadGroup(preffix + "-" + poolNumber.getAndIncrement() + "-threadGroup");

        this.namePrefix = preffix + "-" + poolNumber.getAndIncrement() + "-thread-";
        this.isDaemon = daemon;
    }


    public Thread newThread(Runnable r) {
        Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(),
                -3715992351445876736L);
        t.setDaemon(this.isDaemon);
        if (t.getPriority() != 5) {
            t.setPriority(5);
        }
        return t;
    }

    public ThreadGroup getGroup() {
        return group;
    }
}
