package com.zookeeper.distributedLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/29 0029下午 04:28
 */
@Slf4j
public class ZookeeperLockTest implements Runnable {
    private OrderNumberGenerator orderNumGenerator = new OrderNumberGenerator();//zookeeper分布式锁

    private Lock lock = new ZookeeperLock2();


    @Override
    public void run() {
        getNumber();

    }

    private void getNumber() {
        try {
            lock.getLock();
            String number = orderNumGenerator.GeNumber();
            log.error(Thread.currentThread().getName()+"订单序列号："+number);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unLock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        log.error("##生成唯一订单号##");
        for (int i=0; i<50; i++){
            new Thread(new ZookeeperLockTest()).start();
        }

    }


}
