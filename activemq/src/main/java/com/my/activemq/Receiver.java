package com.my.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 类描述：srpingboot+activeMQ非持久化订阅
 * 创建人：yyf
 * 创建时间：2018/9/29 0029下午 02:26
 */
@Component
public class Receiver {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
//    @JmsListener(destination = "test.queue")
//    public void receiveQueue(String text) {
//        System.out.println("Consumer收到的报文为:" + text);
//    }
}
