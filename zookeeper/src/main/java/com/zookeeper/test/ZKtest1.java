package com.zookeeper.test;

import com.zookeeper.ZookeeperApplication;
import org.apache.zookeeper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/4 0004上午 10:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ZookeeperApplication.class)
public class ZKtest1 {
    //zk连接地址
    private static final String ADDRESS = "127.0.0.7:2181";
    //zk会话超时时间
    private static final int SESSION_OUT_TIME = 2000;
    // 信号量,阻塞程序执行,用户等待zookeeper连接成功,发送成功信号，
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void test() throws IOException, KeeperException, InterruptedException {

        ZooKeeper zk = new ZooKeeper(ADDRESS, SESSION_OUT_TIME, new Watcher() {
            //事件通知
            @Override
            public void process(WatchedEvent watchedEvent) {
                //获取事件状态 -->子线程
                Event.KeeperState keeperState = watchedEvent.getState();
                Event.EventType eventType = watchedEvent.getType();
                //判断为连接状态
                if (Event.KeeperState.SyncConnected == keeperState){
                    //获取事件状态
                    if (Event.EventType.None == eventType){
                        countDownLatch.countDown();
                        System.out.println("zk开始启动连接...");
                    }
                }
            }
        });
        //阻塞  countDown值为0的时候，就不会阻塞线程
        countDownLatch.await();
        // -->主线程
        //持久节点，当zk连接关闭后，持久节点不会被删除，节点信息不允许有重复
        //创建为持久类型，节点开放权限
//        String result = zk.create("/persistentNode", "one".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println("新增持久节点信息:"+result);
        //临时节点，当zk连接关闭后，临时节点信息会被删除。
        String temp_result = zk.create("/tempNode", "one".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("新增临时节点信息:"+temp_result);
        zk.close();
    }
}
