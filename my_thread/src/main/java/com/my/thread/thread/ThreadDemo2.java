package com.my.thread.thread;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/7 0007下午 10:11
 */

public class ThreadDemo2 {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"m1 start...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m1 end...");
    }
    public void m2(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m2");
    }
    public static void main(String[] args) {
        ThreadDemo2 demo2 = new ThreadDemo2();
        new Thread(()->demo2.m1()).start();
        new Thread(()->demo2.m1()).start();
    }
}
