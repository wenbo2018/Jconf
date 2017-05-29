package com.github.wenbo2018.jconf.client.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by wenbo.shen on 2017/5/28.
 */
public interface DistributedLock {

    boolean lock(long time, TimeUnit unit) throws InterruptedException;

    boolean unlock();

}
