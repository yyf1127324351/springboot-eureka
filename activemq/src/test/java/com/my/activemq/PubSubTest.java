package com.my.activemq;

import com.my.activemq.pub_sub.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/10/8 0008下午 04:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PubSubTest {
    @Autowired
    Publisher publisher;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            publisher.publish("test.topic", "Topic Message " + i);
        }
    }

}
