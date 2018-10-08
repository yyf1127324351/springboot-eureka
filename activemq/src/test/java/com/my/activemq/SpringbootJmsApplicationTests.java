package com.my.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/9/29 0029下午 02:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJmsApplicationTests {
//    @Autowired
//    private Sender sender;
//    @Test
//    public void testA(){
//        Destination destination = new ActiveMQQueue("test.queue");
//        for (int i = 0; i < 10; i++) {
//            sender.sendMessage(destination, "hello activemq!!!");
//        }
//    }

}
