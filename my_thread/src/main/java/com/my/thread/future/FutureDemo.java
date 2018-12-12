package com.my.thread.future;

import java.util.concurrent.*;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/6 0006上午 10:40
 */

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer> f = executorService.submit(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        System.out.println(f.get()); //获取返回值
        System.out.println(f.isDone());//是否结束
    }

}
