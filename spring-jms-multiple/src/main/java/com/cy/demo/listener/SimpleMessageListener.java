package com.cy.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author yun.chen
 * @create 2015-12-01 14:50
 */
public class SimpleMessageListener implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageListener.class);

    public void onMessage(Message message) {
        try {
            LOG.info("Received message: {}", ((TextMessage)message).getText());
        } catch (JMSException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
