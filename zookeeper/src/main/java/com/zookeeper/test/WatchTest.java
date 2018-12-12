package com.zookeeper.test;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.junit.Test;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/28 0028下午 11:07
 */

public class WatchTest {

    private static final String ZOOKEEPER_IP_PORT = "localhost:2181";

    private ZkClient zkClient= new ZkClient(ZOOKEEPER_IP_PORT,1000,1000,new SerializableSerializer());

    @Test
    public void watchTest() throws InterruptedException {
        //1.创建一个节点
        String path = "/watcher";
        zkClient.createPersistent(path);
        //2.实例化一个监听器
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {


            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("节点被删除，删除节点为："+dataPath);
            }
        };
        //3.给该节点增加监听器
        this.zkClient.subscribeDataChanges(path,listener);
        Thread.currentThread().join();
    }


}
