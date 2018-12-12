package com.my.thread.threadPool;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/2 0002上午 11:03
 */

public class ThreadPoolTest2 {
    public static void main(String[] args) {
        //启动两个线程去消费
        for (int i = 0; i<2;i++){
            new Thread(()->{
                for (int j = 0;j<20;j++){
                    new ThreadDemo2(j).doSomething();
                }
            },"t"+i).start();
        }
    }
}
