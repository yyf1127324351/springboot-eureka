package com.my.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/9/29 0029上午 11:42
 */
@Service
public class Sender {
    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsTemplate jmsTemplate;
    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination, message);
    }
    @JmsListener(destination="out.queue")
    public void consumerMessage(String text) {
        System.out.println("从out.queue队列收到的回复报文为:" + text);
    }

}
