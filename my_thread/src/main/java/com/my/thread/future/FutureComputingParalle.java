package com.my.thread.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 类描述：newFixedThreadPool  利用线程池和Future计算质数
 * 创建人：yyf
 * 创建时间：2018/11/6 0006上午 11:55
 */

public class FutureComputingParalle {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> result = getPrime(1,20000);
        long end = System.currentTimeMillis();
        System.out.println("单个执行："+ (end-start));
        System.out.println("质数："+result);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        MyTask myTask1 = new MyTask(1,8000);
        MyTask myTask2 = new MyTask(8001,13000);
        MyTask myTask3 = new MyTask(13001,17000);
        MyTask myTask4 = new MyTask(17001,20000);
        Future<List<Integer>> f1 = executorService.submit(myTask1);
        Future<List<Integer>> f2 = executorService.submit(myTask2);
        Future<List<Integer>> f3 = executorService.submit(myTask3);
        Future<List<Integer>> f4 = executorService.submit(myTask4);

        start = System.currentTimeMillis();

        List<Integer> list1 = f1.get();
        List<Integer> list2 = f2.get();
        List<Integer> list3 = f3.get();
        List<Integer> list4 = f4.get();
        end = System.currentTimeMillis();
        System.out.println("多线程执行："+ (end-start));

        System.out.println("质数："+list1+"_"+list2+"_"+list3+"_"+list4);
        executorService.shutdown();





    }

    static class MyTask implements Callable<List<Integer>>{
        int startNum,endNum;

        public MyTask(int startNum, int endNum) {
            this.startNum = startNum;
            this.endNum = endNum;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrime(startNum,endNum);
        }
    }


    private static List<Integer> getPrime(int startNum, int endNum) {
        List<Integer> result = new ArrayList<>();
        for (int i = startNum;i<=endNum;i++){
            if (isPrime(i)){
                result.add(i);
            }
        }
        return result;
    }
    // 返回false说明这个数字不是质数，返回ture说明这个数字是质数
    private static boolean isPrime(int num) {
        for (int i = 2; i<= num/2; i++){
            if (num%i == 0) return false;
        }
        return true;
    }
}
