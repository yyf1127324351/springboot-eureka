package com.my.thread.reentrantLock;

import javax.sql.rowset.FilteredRowSet;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类描述： 利用ReentrantLock和Condition实现
 *        一个固定容量的同步容器，拥有put,get方法，能够支持2个生产者线程，和10个消费者线程的阻塞调用。
 * 创建人：yyf
 * 创建时间：2018/11/2 0002上午 09:50
 */

public class MyContainer2<T> {
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(2);

    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

    private LinkedList<T> linkedList = new LinkedList<T>();
    private int max = 10;
    private int count = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();

    public T get(){
        T t = null;
        lock.lock();
        try {
            while (linkedList.size() == 0){
                notEmpty.await();
            }
            t = linkedList.remove();
            count--;
            notFull.signalAll(); //只唤醒put等待线程
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
        System.out.println("get ....");
        return t;


    }
    public void put(T t){
        lock.lock();
        try {
            while (linkedList.size() == max){
                notFull.await();
            }
            linkedList.add(t);
            count++;
            notEmpty.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();
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
