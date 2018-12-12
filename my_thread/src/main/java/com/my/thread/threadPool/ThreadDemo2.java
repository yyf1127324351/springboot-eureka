package com.my.thread.threadPool;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/2 0002上午 11:07
 */

public class ThreadDemo2 {
    private Integer count;
    int num = 100 ;

    public ThreadDemo2() {
    }

    public ThreadDemo2(Integer count) {
        this.count = count;
    }

    public void doSomething() {

        num--;
        System.out.println(Thread.currentThread().getName()+"ceshi"+count+"自增长值："+ num);
    }
}
