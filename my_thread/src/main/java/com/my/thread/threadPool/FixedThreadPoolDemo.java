package com.my.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类描述： 线程池去执行 FixedThreadPool固定的线程池，任务多的话就进阻塞队列排队。需要最大线程个数，默认KeepAliveTime是0，
 *         阻塞队列为LinkedBlockingQueue
 * 创建人：yyf
 * 创建时间：2018/11/1 0001下午 05:59
 */

public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i<10;i++){
            try {
               threadPool.execute(new ThreadPoolDemo(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }
}
