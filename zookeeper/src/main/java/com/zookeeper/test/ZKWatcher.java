package com.zookeeper.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import javax.crypto.interfaces.PBEKey;
import java.util.concurrent.CountDownLatch;

/**
 * 类描述：Watcher事件通知
 * 创建人：yyf
 * 创建时间：2018/7/4 0004下午 06:00
 */

public class ZKWatcher implements Watcher{

    //zk连接地址
    private static final String CONNECT_ADDRES = "127.0.0.7:2181";
    //zk会话超时时间
    private static final int SESSION_OUT_TIME = 2000;
    // 信号量,阻塞程序执行,用户等待zookeeper连接成功,发送成功信号，
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    private ZooKeeper zk;

    @Test
    public void test() throws InterruptedException {
        createConnection(CONNECT_ADDRES,SESSION_OUT_TIME);
        createNode("/create4","node2");
        updateNode("/create4","node23");
    }

    public void createConnection(String addres, int sessionTimeOut) {
        try {
            zk = new ZooKeeper(CONNECT_ADDRES, sessionTimeOut, this);
            System.out.println("zk 开始启动连接服务器....");
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        // 获取事件状态
        Event.KeeperState keeperState = watchedEvent.getState();
        Event.EventType eventType = watchedEvent.getType();
        String path = watchedEvent.getPath();
        System.out.println("进入到process方法,事件通知 Path:" + path);
        if (Event.KeeperState.SyncConnected == keeperState) {
            if (Event.EventType.None == eventType) {
                // 建立连接状态
                System.out.println("zk开始连接...");
                countDownLatch.countDown();
            } else if (Event.EventType.NodeCreated == eventType) {
                System.out.println("事件通知,新增note节点:" + path);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (Event.EventType.NodeDataChanged == eventType) {
                System.out.println("事件通知,当前node节点" + path + "被修改....");
                String updateNodeData = readNodeData(path,true);
                System.out.println("----------"+updateNodeData);
            } else if (Event.EventType.NodeDeleted == eventType) {
                System.out.println("事件通知,当前node节点" + path + "被删除....");
            }

        }
    }
    //是否开启事件通知 事件监听 needWatch=ture开启
    public Stat exists(String path, boolean needWatch) {
        try {
            return this.zk.exists(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readNodeData(String nodePath,boolean needWatch) {
        String data = "";
        try {
            //zookeeper watch事件只触发一次,//zookeeper watch事件只触发一次,
            //参数2：为true时表示持续性的去监控，监控的watcher实例为上一个watcher对象
            //还有另一种持续性监控的方法就行创建一个Watcher对象。
            zk.getData(nodePath, needWatch, null);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
        return data;
    }

    public boolean createNode(String nodePath,String value){
        try {
            exists(nodePath,true);
            String result = zk.create(nodePath, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("节点创建成功, nodePath:" + nodePath + ",value:" + value);
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateNode(String nodePath,String value){
        exists(nodePath,true);
        try {
            this.zk.setData(nodePath, value.getBytes(), -1);
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void close(){
        if (zk != null){
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
