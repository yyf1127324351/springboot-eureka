package com.activity.service;

import org.apache.log4j.Logger;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/15 0015下午 06:52
 */

public class DianXiaoNoPayThread implements Runnable {
    private static Logger loger = Logger.getLogger(DianXiaoNoPayThread.class);

    private String loanId;

    public DianXiaoNoPayThread(String loanId) {
        this.loanId = loanId;
    }


    @Override
    public void run() {
        loger.error("sync-OperaOverdueDataThread:" + loanId);
        System.out.println(Thread.currentThread().getName() + "_" + loanId);

    }
}
