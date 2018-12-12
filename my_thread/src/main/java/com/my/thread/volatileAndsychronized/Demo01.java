package com.my.thread.volatileAndsychronized;

import java.util.concurrent.TimeUnit;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/10/30 0030下午 05:53
 */

public class Demo01 {
    volatile boolean running = true;
    public void me (){
        System.out.println("me start");
        while (running){

        }
        System.out.println("me end");
    }
    public static void  main(String[] args){
        Demo01 demo01 = new Demo01();
        new Thread(demo01::me,"thread1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo01.running = false;
    }
}
