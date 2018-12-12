package com.my.thread.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/1 0001下午 03:49
 */

public class ReentrantLockTest extends Thread {

    private static ReentrantLock lock = new ReentrantLock(true);// 默认为非公平锁，如果设置为true则是公平锁。

    public void run(){
        for (int i = 0;i<100;i++){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁"+i);
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest r = new ReentrantLockTest();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();


    }
}
