package com.my.thread.future;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/6 0006下午 02:28
 */

public class A {

    public static void main(String[] args) {

        List<Integer> result = getPrime(1,20000);

    }

    private static List<Integer> getPrime(int startNum , int endNum) {
        List<Integer> result = new ArrayList<>();
        for (int i = startNum;i<= endNum;i++){
            if (isPrime(i)) result.add(i);
        }
        return result;
    }

    public static boolean isPrime(Integer num){
        for (int i = 2; i<= num/2; i++){
            if (num%i == 0) return false;
        }
        return true;
    }
}
