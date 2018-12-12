package com.my.thread.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/10/26 0026下午 06:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CallableFutureTest {
    @Test
    public void test(){

        // 定义3个Callable类型的任务
        Callable task0 = new CallableDemo(0);
        Callable task1 = new CallableDemo(1);
        Callable task2 = new CallableDemo(2);
        Runnable runnable1 = new RunnableDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            Future future0 = executorService.submit(task0);  //传一个Callable对象，返回一个Future对象。 提交并执行任务1，任务启动时返回了一个Future对象
            System.out.println("task0执行结果 ：" + future0.get());	// 获得第一个任务的结果，如果调用get方法,直到当前线程会等待任务执行完毕后才往下执行
            Future future1 = executorService.submit(task1);	// 提交并执行任务2，任务启动时返回了一个Future对象
            String result1=null;
            try{
                result1=(String) future1.get(6,TimeUnit.SECONDS);	// 如有必要，最多等待6秒之后，获取其结果（如果结果可用），因为此时任务在无限循环肯定超时
            }catch(TimeoutException e){
                System.out.println("future1用get获取超时了！此时任务1的执行结果："+result1);
            }

            Future future2 = executorService.submit(task2);
            System.out.println("任务2开始执行了.....");
            Thread.sleep(2000);		//主线程即当前线程停止2秒往下执行
            System.out.println("task1被cancel()取消任务，结果: " + future1.cancel(true));	//中断任务1的循环
            System.out.println("task2执行结果：" + future2.get());

            executorService.execute(runnable1);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
