package com.my.thread.threadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 类描述：ForkJoinPool 将大任务分成小任务执行fork(),然后合并成一个线程。由于是精灵线程，demon所以主线程.read才能看到输出结果
 *         如果继承RecursiveAction则没有返回值。如果继承RecursiveTask<Long>则会有返回值，最后需要 task1.join+task2.join
 *         join本来就是阻塞的，所以不用.read就能输出结果.大规模的数据计算
 * 创建人：yyf
 * 创建时间：2018/11/7 0007上午 09:37
 */

public class ForkJoinPoolDemo {
    static int[] nums = new int[1000000];
    static  final int MAX_NUM = 50000;
    static Random r = new Random();
    static {
        for (int i=0; i< nums.length; i++){
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());//jdk8 算总和
    }
    /*
    static class AddTask extends RecursiveAction{
        int start,end;
        AddTask(int s,int e){
            this.start = s;
            this.end = e;
        }


        @Override
        protected void compute() {
            if (end - start <= MAX_NUM){
                long sum = 0L;
                for (int i=start;i<end;i++) sum += nums[i];
                System.out.println("from:"+start+"to:"+end+"="+sum);
            }else {
                int middle = start + (end-start)/2;
                AddTask subTask1 = new AddTask(start,middle);
                AddTask subTask2 = new AddTask(middle,end);
                subTask1.fork();
                subTask2.fork();
            }

        }

    }
    */

    static class AddTask extends RecursiveTask<Long>{
        int start,end;
        AddTask(int s,int e){
            this.start = s;
            this.end = e;
        }


        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM){
                long sum = 0L;
                for (int i=start;i<end;i++) sum += nums[i];
                return sum;
            }else {
                int middle = start + (end-start)/2;
                AddTask subTask1 = new AddTask(start,middle);
                AddTask subTask2 = new AddTask(middle,end);
                subTask1.fork();
                subTask2.fork();
                return subTask1.join()+subTask2.join();
            }

        }

    }

    public static void main(String[] args) throws IOException {
//        ForkJoinPool fjp = new ForkJoinPool();
//        AddTask task = new AddTask(0,nums.length);
//        fjp.execute(task);
//        System.in.read();
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask task = new AddTask(0,nums.length);
        fjp.execute(task);
        long result = task.join();
        System.out.println(result);
    }
}
