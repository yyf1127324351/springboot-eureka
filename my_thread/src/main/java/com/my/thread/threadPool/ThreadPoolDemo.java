package com.my.thread.threadPool;

import java.security.cert.TrustAnchor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/1 0001下午 06:02
 */

public class ThreadPoolDemo implements Runnable {
    private Integer count;
    int num = 100 ;
    ReentrantLock lock = new ReentrantLock();

    public ThreadPoolDemo() {
    }

    public ThreadPoolDemo(Integer count) {
        this.count = count;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            num--;
            System.out.println(Thread.currentThread().getName()+"ceshi"+count+"自增长值："+ num);
        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
