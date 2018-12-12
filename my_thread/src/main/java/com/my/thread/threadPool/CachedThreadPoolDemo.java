package com.my.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 类描述：CachedThreadPool 缓存的线程池，初始化没有线程，来一个任务启一个线程，任务多的话，如果有空闲线程直接用空闲的线程，如果没有空闲的就一直启线程。
 *        默认的情况下，线程空闲60秒，会自动被销毁。KeepAliveTime默认为60.默认核心线程池大小0，线程池最大线程数Integer.MAX_VALUE,
 *        阻塞队列：SynchronousQueue
 * 创建人：yyf
 * 创建时间：2018/11/6 0006下午 02:38
 */

public class CachedThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i<3;i++){
            executorService.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(executorService);
        TimeUnit.SECONDS.sleep(80);
        System.out.println(executorService);
    }
}
