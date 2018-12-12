package com.my.thread.threadPool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 类描述：定时器线程池,
 *       阻塞队列DelayedWorkQueue
 * 创建人：yyf
 * 创建时间：2018/11/6 0006下午 06:10
 */

public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        executorService.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0,500, TimeUnit.MILLISECONDS);
    }
}
//        结果
//        pool-1-thread-1
//        pool-1-thread-1
//        pool-1-thread-2
//        pool-1-thread-1
//        pool-1-thread-1
//        pool-1-thread-2
//        pool-1-thread-4
//        pool-1-thread-4
//        pool-1-thread-4
//        pool-1-thread-4
//        pool-1-thread-4
//        pool-1-thread-4