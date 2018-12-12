package com.my.thread.threadLocal;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/8 0008下午 01:58
 */

public class ThreadLocalHolder {
    static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void set(String param){
        threadLocal.set(param);
    }
    public static String get(){
        return threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }
}
