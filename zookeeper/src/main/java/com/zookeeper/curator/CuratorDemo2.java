package com.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/16 0016上午 11:19
 */

public class CuratorDemo2 {
    @Test
    public void test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //创建Curator客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();
        for (int i = 0; i < 10; i++){
            MultJVMLock multJVMLock = new MultJVMLock(i,client, latch);
            new Thread(multJVMLock).start();
            Thread.sleep(300);
        }
        latch.await();
        client.close();

    }

    public static class MultJVMLock implements Runnable {
        private int num;
        private CuratorFramework client;
        private CountDownLatch latch;
        public MultJVMLock (int num,CuratorFramework client,CountDownLatch latch){
            this.num = num;
            this.client = client;
            this.latch = latch;
        }

        @Override
        public void run() {

            //InterProcessMutex进程互斥，实现分布式锁。可重入式锁
            InterProcessMutex lock = new InterProcessMutex(client, "/mylock");

            try {
                System.out.println("我是第" + num + "号线程，我开始获取锁");
                lock.acquire();
                System.out.println("我是第" + num + "号线程，我已经获取锁");
                Thread.sleep(10000);
                latch.countDown();
            }catch (Exception e){

            }finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }
}