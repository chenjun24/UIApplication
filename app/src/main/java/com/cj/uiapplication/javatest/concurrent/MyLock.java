package com.cj.uiapplication.javatest.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 在A Q S中维护了一个同步状态变量state，getState函数获取同步状态，setState、compareAndSetState函数修改同步状态，
 * 对于A Q S来说，线程同步的关键是对state的操作，可以说获取、释放资源是否成功都是由state决定的，比如state>0代表可获取资源，
 * 否则无法获取，所以state的具体语义由实现者去定义，现有的ReentrantLock、ReentrantReadWriteLock、Semaphore、
 * CountDownLatch定义的state语义都不一样。
 */
public class MyLock implements Lock {
    private  Helper helper =new Helper();

    private class Helper extends AbstractQueuedSynchronizer{
        protected Helper() {
            super();
        }

        /**
         * 独占式获取资源，子类实现
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            Thread thread = Thread.currentThread();//获取当前线程
            int state = getState(); //获取AbstractQueuedSynchronizer中记录的状态 0 代表还没有线程获取锁
            if (state == 0){
                  //如果没有线程拿到锁，则对当前线程进行cas操作设置锁状态标志，如果cas操纵成功则
                if (compareAndSetState(0,arg)){
                    //将AbstractQueuedSynchronizer记录的线程设置为当前线程，然后返回true代表 获取锁成功
                    setExclusiveOwnerThread(thread);
                    return true;
                }
            }
            //如果当前锁的状态不为0，则判断AbstractQueuedSynchronizer中保存的线程是否是当前线
            //程，如果是则将所状态标志位加arg，返回true成功。
            else if(getExclusiveOwnerThread() == thread){
                setState(state+arg);
                return true;
            }
            //否则返回false失败
            return false;
        }

        /**
         * 独占式释放资源，子类实现
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            Thread thread = Thread.currentThread();//获取当前线程
            int state = getState(); //获取AbstractQueuedSynchronizer中记录的状态 0 代表还没有线程获取锁
            //判断当前线程和AbstractQueuedSynchronizer中记录的线程是否一致，不一致返回一个运行时异常
            if (getExclusiveOwnerThread() != thread){
                  throw new RuntimeException();
            }
            //计算释放一次锁之后线程的状态，如果锁状态为0，则释放所成功并将
           // AbstractQueuedSynchronizer中保存的当前线程设置为null
           state = state-arg;
            if (state == 0){
                setExclusiveOwnerThread(null);
                return true;
            }
            //设置计算之后的锁状态
            setState(state);
            return false;
        }

        protected Condition newCondition(){
            return  new ConditionObject();
        }

        /**
         * 共享式获取资源，返回值大于等于0则表示获取成功，否则获取失败，子类实现
         * @param arg
         * @return
         */
        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        /**
         * 共享式释放资源，子类实现
         * @param arg
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        /**
         * 当前线程是否持有资源
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }
    }


    @Override
    public void lock() {
       helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }
}
