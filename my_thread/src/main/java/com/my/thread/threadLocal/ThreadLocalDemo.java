package com.my.thread.threadLocal;

import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;

import java.util.concurrent.TimeUnit;

/**
 * 类描述：ThreadLocal线程局部变量，或者叫线程本地变量。ThreadLocal为变量在每个线程中都创建了一个副本，每个线程可以访问自己内部的副本变量。
 * 创建人：yyf
 * 创建时间：2018/11/7 0007下午 04:07
 */

public class ThreadLocalDemo {
    static final ThreadLocal<String> threadLocal = new ThreadLocal<>();




    public static String get(){
        return threadLocal.get();
    }

    public static void main(String[] args) {

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("aaa");
            threadLocal.set("abc");
//            System.out.println("第一个线程"+Thread.currentThread().getName()+"-"+threadLocal.get());
        }).start();

//
//        new Thread(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("第二个线程"+Thread.currentThread().getName()+"-"+threadLocal.get());
//        }).start();





    }
}
