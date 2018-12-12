package com.zookeeper.lock;

import com.zookeeper.distributedLock.OrderNumberGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/13 0013上午 10:59
 */

public class LockTest implements Runnable {

    //private OrderNumberGeneratorSynchronized orderNumGenerator = new OrderNumberGeneratorSynchronized();//使用synchronized

    private OrderNumberGenerator orderNumGenerator = new OrderNumberGenerator();//zookeeper分布式锁
    //模拟50个并发
    private static CountDownLatch countDownLatch = new CountDownLatch(50);

    private static List<String> result = new ArrayList<>();

    @Override
    public void run() {
        countDownLatch.countDown();
//        orderNumGenerator.getNumber();
        result.add(orderNumGenerator.GeNumber());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("##生成唯一订单号##");
        for (int i=0; i<50; i++){
            new Thread(new LockTest()).start();
        }
        countDownLatch.await();
        Thread.sleep(10000);
        Collections.sort(result);
        for (String str:result){
            System.out.println(str);
        }

    }
}
