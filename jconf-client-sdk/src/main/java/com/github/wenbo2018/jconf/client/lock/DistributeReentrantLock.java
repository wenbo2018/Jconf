package com.github.wenbo2018.jconf.client.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * Created by wenbo.shen on 2017/5/28.
 */
public class DistributeReentrantLock implements DistributedLock,Serializable {

    private static final long serialVersionUID = 6047939081333141456L;


    @Override
    public boolean lock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }
}
