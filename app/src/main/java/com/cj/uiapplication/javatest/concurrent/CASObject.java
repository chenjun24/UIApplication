package com.cj.uiapplication.javatest.concurrent;


import java.util.concurrent.atomic.AtomicInteger;

public class CASObject {
    public AtomicInteger count =new AtomicInteger(100);
    public void sub() {
        count.decrementAndGet();//刚开始读取的时候是A，更新的时候也是A，但中间可能会被改为B过了。
    }
}
