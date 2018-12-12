package com.my.thread.thread;

import com.my.thread.threadPool.ThreadPoolDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/7 0007下午 09:18
 */

public class ThreadDemo implements Runnable {

    ReentrantLock lock = new ReentrantLock();

    int i= 10;

    @Override
    public void run() {
        m1();
//        m2();

    }

    public void m1(){

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"m1 start..."+ ++i);
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+"m1 end..."+ ++i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();

    }
    public void m2(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m2");
    }


    public static void main(String[] args) {
        ThreadDemo demo  = new ThreadDemo();
        new Thread(demo).start();
        new Thread(demo).start();
    }
}
