package com.my.thread.reentrantLock;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 类描述：使用synchronized 实现
 *        一个固定容量的同步容器，拥有put,get方法，能够支持2个生产者线程，和10个消费者线程的阻塞调用。
 * 创建人：yyf
 * 创建时间：2018/11/2 0002上午 09:50
 */

public class MyContainer1<T> {
    private LinkedList<T> linkedList = new LinkedList<T>();
    private int max = 10;
    private int count = 0;

    public synchronized T get(){
        T t = null;
        while (linkedList.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = linkedList.remove();
        count --;
        this.notifyAll(); //唤醒所有等待线程
        return t;
    }
    public synchronized void put(T t){
        while (linkedList.size()== max){ //这里使用while的原因是，循环判断，如果用if 当线程被换醒的时候， 可能有两个线程被唤醒，一个线程执行到了向list添加的时候，另                                            一个已经线程已经添加过了，然而此时不会再去判断当前是否是MAX，第一个线程还会往list中添加。而while在添加前还会再判断一次。
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        linkedList.add(t);
        ++count;
        this.notifyAll(); //不使用notify是因为，只唤醒一个线程如果此时唤醒的还是put的，仍旧会被阻塞。
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        //启动消费者线程
        for (int i = 0 ;i<10;i++){
            new Thread(()->{
                for (int j= 0;j<5;j++){
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //生产者线程
        for (int i = 0; i<2;i++){
            new Thread(()->{
                for (int j= 0;j<25;j++){
                    c.put(Thread.currentThread().getName()+"_"+j);
                }
            }).start();
        }
    }

}
