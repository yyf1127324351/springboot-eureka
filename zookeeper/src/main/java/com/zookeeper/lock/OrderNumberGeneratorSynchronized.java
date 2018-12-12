package com.zookeeper.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类描述：订单编号生成器 (单机)
 * 创建人：yyf
 * 创建时间：2018/11/13 0013上午 11:06
 */

public class OrderNumberGeneratorSynchronized {
    public static int count = 0;
    public synchronized void getNumber(){
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        System.out.println(simp.format(new Date())+"-"+ ++count);
    }

    public String GeNumber(){
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simp.format(new Date())+"-"+ ++count;
    }

}
