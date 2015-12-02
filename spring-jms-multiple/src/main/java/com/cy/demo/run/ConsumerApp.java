package com.cy.demo.run;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yun.chen
 * @create 2015-12-01 14:34
 */
public class ConsumerApp {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("/spring/consumer-jms-context.xml", ConsumerApp.class);
    }
}
