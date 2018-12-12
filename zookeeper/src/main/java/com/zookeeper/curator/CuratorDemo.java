package com.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

/**
 * 类描述：Curator实现zookeeper分布式锁
 * 创建人：yyf
 * 创建时间：2018/7/10 0010下午 03:54
 */

public class CuratorDemo {
    @Test
    public void test() throws InterruptedException {
        //操作失败重试机制   间隔1000毫秒   尝试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //创建Curator客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();

        //InterProcessMutex进程互斥，实现分布式锁。可重入式锁
        final InterProcessMutex lock = new InterProcessMutex(client, "/mylock");
        System.out.println(lock);

        try {
            lock.acquire();
            System.out.println("已经获取锁资源");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //释放锁
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(5000);
        client.close();

    }
}
