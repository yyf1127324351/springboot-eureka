package com.zookeeper.distributedLock;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.bouncycastle.cms.PasswordRecipientId;

import java.util.concurrent.CountDownLatch;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/13 0013下午 03:39
 */

public class ZookeeperLock extends AbstactLock {

    private static final String ZOOKEEPER_IP_PORT = "localhost:2181";
    private static CountDownLatch countDownLatch = null;

    private ZkClient zkClient= new ZkClient(ZOOKEEPER_IP_PORT,1000,1000,new SerializableSerializer());
    private static final String PATH = "/lock";
    private static final String PATH2 = "/lock2";

    //尝试获得锁
    @Override
    public boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null){
                    countDownLatch.countDown();
                    System.out.println("-------------------------------------------------------------------------");
                }

            }
        };
        //注册事件
        zkClient.subscribeDataChanges(PATH,listener);
        if (zkClient.exists(PATH)){
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //解除监听
        zkClient.unsubscribeDataChanges(PATH,listener);

    }

    @Override
    public void unLock() {
        if (zkClient != null){
            zkClient.delete(PATH);
            zkClient.close();
            System.out.println("释放锁资源");
        }
    }
}
