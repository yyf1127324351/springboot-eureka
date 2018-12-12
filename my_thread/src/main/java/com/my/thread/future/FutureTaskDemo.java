package com.my.thread.future;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类描述：FutureTask<> 实现了RunnableFuture<V> --> extends Runnable, Future<V>
 * 创建人：yyf
 * 创建时间：2018/11/6 0006上午 11:28
 */

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<Integer>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        System.out.println(task.get());
        System.out.println(task.isDone());
    }
}
