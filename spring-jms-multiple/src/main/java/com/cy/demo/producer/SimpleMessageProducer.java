package com.cy.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;

/**
 * @author yun.chen
 * @create 2015-12-01 14:47
 */
public class SimpleMessageProducer {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageProducer.class);

    protected JmsTemplate jmsTemplate;

    protected int numberOfMessages = 100;

    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessages() throws JMSException {
        final StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < numberOfMessages; ++i) {
            buffer.append("Message '").append(i).append("' sent at: ").append(new Date());

            final int count = i;
            final String payload = buffer.toString();

            jmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(payload);
                    message.setIntProperty("messageCount", count);
                    LOG.info("Sending message number '{}'", count);
                    return message;
                }
            });
            buffer.setLength(0);
        }
    }
}
