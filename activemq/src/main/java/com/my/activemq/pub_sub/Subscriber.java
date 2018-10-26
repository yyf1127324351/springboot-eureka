package com.my.activemq.pub_sub;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * 类描述：发布/订阅模式
 * 创建人：yyf
 * 创建时间：2018/10/8 0008下午 04:43
 */
@Service
public class Subscriber {
    @JmsListener(destination = "test.topic", containerFactory = "jmsTopicListener")
    public void subscribe(String text) {
        System.out.println("===========<<<<<<<<收到订阅的消息" + text);
    }
}
