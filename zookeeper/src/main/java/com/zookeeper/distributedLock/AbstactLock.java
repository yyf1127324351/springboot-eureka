package com.zookeeper.distributedLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/13 0013下午 03:26
 */
@Slf4j
public abstract class AbstactLock implements Lock {
    @Override
    public void getLock() {
        //尝试获取锁资源
        if(tryLock()){
            log.error("线程"+Thread.currentThread().getName()+"拿到了锁！！！");
        }else {
            //等待
            waitLock();
            //重新获取锁
            getLock();
        }

    }
    //尝试获取锁资源
    public abstract boolean tryLock();
    //等待
    public abstract void waitLock();


    @Override
    public void unLock() {

    }
}
