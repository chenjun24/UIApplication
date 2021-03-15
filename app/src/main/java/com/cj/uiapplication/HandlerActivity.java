package com.cj.uiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/**
 * 关于handler的几个问题
 * 1.主线程可以有多少个handler--没有限制
 * 2.主线程有几个looper--一个 一个线程 -个looper-一个messageQueue
 * 3.是如果保证一个线程只有一个looper
 *      looper的构造函数是私有的，只能通过静态函数prepare()创建
 *       而保存looper对是用
 *       static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();静态常量
 *       ThreadLocal保存looper 是每个thread只有一份 使用每个线程中的ThreadLocalMap<ThreadLocal,Value>存储
 *4.如何在线程中使用Handler，同时要注意什么，消息处理完成之后 怎么停止
 *    使用handlerThread 或者自己通过looper.prepare()  looper.loop() 主线程安全 quitSafety()
 *5.消息机制怎么保证消息并发安全
 *     消息入队和加入队列的过程都加了锁 所以是安全的
 *6.没有消息处理时 主线程会进入休眠状态   不会出现anr
 */
public class HandlerActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

    }

    private void test(){
        MyThread thread = new MyThread();
        //Handler handler = new Handler(getMainLooper());
        Handler handler = new Handler(thread.getLooper());
        Message obtain = Message.obtain();//创建message使用这种方式 享元模式
        handler.sendMessage(obtain);
        thread.quitSafely();
        //HandlerThread
    }

    /**
     * 最好使用静态内部类的方式创建handler类
     * 如果是使用匿名内部类 则持有外部引用(activity)
     * 而message持有handler的引用(msg中的target指向handler)
     * 而messageQueue中持有message
     * 所以如果是消息是延时处理的话 就会导致activity回收不了
     * 因而另外一种方式就是在activity 销毁时 handler remove所有消息
     */

    static class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }


    /**
     * 自定义的子线程如果要创建loop消息机制
     * 要注意多线程并发问题 获取looper
     * Android已经有实现的HandlerThread
     */
    class MyThread extends Thread{
        Looper mLooper;

        public Looper getLooper() {
            if (!isAlive())
                return null;
            synchronized (this){
                while (isAlive() && mLooper==null){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return mLooper;
        }

        @Override
        public void run() {
            super.run();
            Looper.prepare();
            synchronized (this){
                mLooper = Looper.myLooper();
                notifyAll();//这个执行   wait的线程并不一定会立即执行 只是状态变为就绪状态
            }
            Looper.loop();
        }

        //如果子线程的消息都处理完了 要停止loop工作 不然 线程一直在运行占用资源
        public boolean quitSafely() {
                mLooper = getLooper();
                if (mLooper!= null){
                    mLooper.quitSafely();
                    return true;
                }
                return false;
        }
    }

}