package com.my.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/10/8 0008上午 11:19
 */
@Component
public class Receiver2 {
//    @JmsListener(destination = "test.queue")
//    @SendTo("out.queue")
//    public String receiveQueue(String text) {
//        System.out.println("Consumer2收到的报文为:" + text);
//        return "return message" + text;
//    }
}
