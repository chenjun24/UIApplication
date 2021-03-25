package com.cj.uiapplication.javatest.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockObject {
    public int count =100;
    public Lock lock = new ReentrantLock();
    public void sub() {
        lock.lock();//compareAndSetState 是通过无锁方式实现的
        count--;
        lock.unlock();
    }
}
