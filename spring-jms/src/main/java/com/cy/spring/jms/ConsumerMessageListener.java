package com.cy.spring.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息消费者
 *
 * @author yun.chen
 * @create 2015-12-01 11:15
 */
public class ConsumerMessageListener implements MessageListener {

    public void onMessage(Message message) {
        //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
        TextMessage textMsg = (TextMessage) message;
        System.out.println("接收到一个纯文本消息。");

        try {
            System.out.println("消息内容是：" + textMsg.getText());
            String str = message.getStringProperty("Hello");
            System.out.println("自定义信息是：" + str);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
