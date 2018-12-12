package com.my.thread.threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/8 0008下午 02:00
 */

public class ThreadLocalHolderTest {

    public static void doGets() throws InterruptedException {
        String param = ThreadLocalHolder.get();
        System.out.println(Thread.currentThread().getName()+"get-->"+param);
//        ThreadLocalHolder.remove();
//        TimeUnit.SECONDS.sleep(20);
    }

    public static void doSets() throws InterruptedException {
        ThreadLocalHolder.set("aaaaaaaaaa");
        System.out.println(Thread.currentThread().getName()+"set");
//        TimeUnit.SECONDS.sleep(20);
    }



    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            try {
                doSets();
                doGets();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                doGets();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();





    }
}
