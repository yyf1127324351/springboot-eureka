package com.zookeeper.distributedLock;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/30 0030上午 10:07
 */
@Slf4j
public class ZookeeperLock2 extends AbstactLock {

    private static final String ZOOKEEPER_IP_PORT = "localhost:2181";
    private ZkClient zkClient= new ZkClient(ZOOKEEPER_IP_PORT,1000,1000,new SerializableSerializer());
    private static CountDownLatch countDownLatch = null;

    private static final String PATH2 = "/lock2";
    private String beforePath;
    private String currentPath;

    public ZookeeperLock2() {
        if (!zkClient.exists(PATH2)){
            zkClient.createPersistent(PATH2);
        }
    }

    @Override
    public boolean tryLock() {
        log.error(Thread.currentThread().getName()+"当前线程尝试获取锁："+currentPath);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //如果currentPath为空，则为第一次尝试加锁，第一次加锁赋值currentPath
        if (currentPath == null || currentPath.length()<=0){
            currentPath = zkClient.createEphemeralSequential(PATH2+'/',"lock");
            log.error(Thread.currentThread().getName()+"当前节点"+currentPath);

        }
        //获取所有临时节点并排序，临时节点名称为自增长的字符串如：00000010
        List<String> childrenNodes = zkClient.getChildren(PATH2);
        log.error(Thread.currentThread().getName()+"输出节点集合为============="+childrenNodes);
        Collections.sort(childrenNodes);
        //如果当前节点在所有节点中排名第一，则获得锁
        if (currentPath.equals(PATH2+'/'+childrenNodes.get(0))){
            return true;
        }else {
            //如果当前节点在所有节点中排名不是第一，则获取前面的节点，并赋值给beforePath
            int wz = Collections.binarySearch(childrenNodes,currentPath.substring(7));
            beforePath = PATH2+'/'+childrenNodes.get(wz-1);
            log.error(Thread.currentThread().getName()+"前面的节点为"+beforePath);
        }


        return false;
    }

    @Override
    public void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                if (countDownLatch != null){
                    countDownLatch.countDown();
                }
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        };
        //给排在前面的节点添数据删除的watcher，本质是启动另外一个线程去监控前置节点
        zkClient.subscribeDataChanges(beforePath,listener);
        //如果上一个节点还存在的话，就只能await
        if (zkClient.exists(beforePath)) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //如果当前锁被释放，并countDownLatch.countDown()，则执行下面语句，取消对改节点的监听
        zkClient.unsubscribeDataChanges(PATH2,listener);

    }

    @Override
    public void unLock() {
        zkClient.delete(currentPath);
        log.error("线程"+Thread.currentThread().getName()+"释放了锁，拜拜！");
        zkClient.close();
    }
}
