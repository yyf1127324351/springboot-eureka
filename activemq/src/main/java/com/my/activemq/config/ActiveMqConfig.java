package com.my.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/9/29 0029下午 03:48
 */
@Configuration
@EnableJms
public class ActiveMqConfig{

    @Bean
    public JmsTemplate getJmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDeliveryMode(2);//进行持久化配置 1表示非持久化，2表示持久化
        activeMQConnectionFactory.setTrustAllPackages(true);//使用实体直接入队时配置，字符串入队列，不需要，为了演示将整个实体件入队传输。
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
        jmsTemplate.setSessionAcknowledgeMode(1);//客户端签收模式
        return jmsTemplate;
    }

    @Bean(name = "jmsTopicListener")
    JmsListenerContainerFactory<?> jmsListenerContainerFactory (ConnectionFactory connectionFactory){
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
