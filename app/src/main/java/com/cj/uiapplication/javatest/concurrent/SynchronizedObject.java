package com.cj.uiapplication.javatest.concurrent;

public class SynchronizedObject {
    public int count =100;
    public synchronized void sub() {
        count--;
    }
}
