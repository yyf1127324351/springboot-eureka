package com.my.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/7 0007上午 11:59
 */

public class ParalleStreamAPIDemo {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i=0;i<10000; i++) nums.add(1000000+r.nextInt(1000000));
        long start = System.currentTimeMillis();
        nums.forEach(v->isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println("单个执行："+ (end-start));

        //使用parallel stream api
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(ParalleStreamAPIDemo::isPrime);
        end = System.currentTimeMillis();
        System.out.println("多线程执行："+ (end-start));



    }
    // 返回false说明这个数字不是质数，返回ture说明这个数字是质数
    private static boolean isPrime(int num) {
        for (int i = 2; i<= num/2; i++){
            if (num%i == 0) return false;
        }
        return true;
    }
}
