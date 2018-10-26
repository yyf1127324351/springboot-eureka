package com.my.activemq.pub_sub;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 类描述：发布/订阅模式
 * 创建人：yyf
 * 创建时间：2018/10/8 0008下午 04:41
 */
@Service
public class Publisher {
    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsTemplate jmsTemplate;

    public void publish(String destinationName, String message) {
        Destination destination = new ActiveMQTopic(destinationName);
        System.out.println("============>>>>> 发布topic消息 " + message);
        jmsTemplate.convertAndSend(destination, message);
    }
}
