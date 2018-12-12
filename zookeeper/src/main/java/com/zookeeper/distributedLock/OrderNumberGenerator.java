package com.zookeeper.distributedLock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/13 0013上午 11:53
 */

public class OrderNumberGenerator {
    public static int count = 0;
    public void getNumber(){
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        System.out.println(simp.format(new Date())+"-"+ ++count);
    }

    public String GeNumber(){
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simp.format(new Date())+"-"+ ++count;
    }
}
