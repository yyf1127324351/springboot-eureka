package com.zookeeper.distributedLock;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/13 0013下午 03:25
 */

public interface Lock {
    //获取锁
    public void getLock();
    //释放所
    public void unLock();
}
