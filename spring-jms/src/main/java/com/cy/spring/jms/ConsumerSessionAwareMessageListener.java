package com.cy.spring.jms;

import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 可以发送通知信息的消费者
 *
 * @author yun.chen
 * @create 2015-12-01 11:35
 */
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<TextMessage>{

    private Destination destination;

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void onMessage(TextMessage message, Session session) throws JMSException {
        System.out.println("收到一条消息");
        System.out.println("消息内容是：" + message.getText());
        MessageProducer producer = session.createProducer(destination);
        Message textMessage = session.createTextMessage("ConsumerSessionAwareMessageListener。。。");
        producer.send(textMessage);
    }
}
