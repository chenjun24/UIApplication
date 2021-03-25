package com.cj.uiapplication.javatest.concurrent;

import java.util.HashMap;

public class ConCurrentTest {
    public static void main(String[] ars){
       // SynchronizedObject object = new SynchronizedObject();
        //LockObject object = new LockObject();
        CASObject object = new CASObject();
        System.out.println("before count:"+object.count);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<50;i++){
                    object.sub();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thread1 count:"+object.count);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<50;i++){
                    object.sub();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thread2 count:"+object.count);
            }
        });
        thread1.start();
        thread2.start();
    }
}
