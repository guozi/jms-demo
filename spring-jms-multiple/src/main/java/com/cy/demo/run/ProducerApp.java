package com.cy.demo.run;

import com.cy.demo.producer.SimpleMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.JMSException;

/**
 * @author yun.chen
 * @create 2015-12-01 14:30
 */
public class ProducerApp {
    private static final Logger LOG = LoggerFactory.getLogger(ProducerApp.class);

    public static void main(String[] args) throws JMSException {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/producer-jms-context.xml", ProducerApp.class);
        SimpleMessageProducer producerService = (SimpleMessageProducer) context.getBean("messageProducer");
        producerService.sendMessages();
    }
}
