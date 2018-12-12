package com.my.thread.thread;

import java.util.concurrent.Callable;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/10/26 0026下午 06:19
 */

public class CallableDemo implements Callable {

    public int testData = 0;

    public CallableDemo(int testData){
        this.testData = testData;
    }


    @Override
    public String call() throws Exception {
        if (this.testData == 0){
            return "testData = 0";
        }
        if (this.testData == 1){
            try {
                while (true) {
                    System.out.println("任务1执行中.....");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("任务1中断了.....");
            }
            return "如果被cancle，则不会接受返回值！";
        } else {
            Thread.sleep(10000);
            return "任务2睡了10秒！";
        }
    }
}
