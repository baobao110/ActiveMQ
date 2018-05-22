package com.jms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerListener implements MessageListener{

    private static final Logger logger=LoggerFactory.getLogger(ConsumerListener.class);

    public void onMessage(Message message) {
        TextMessage textMessage=(TextMessage)message;
        try {
            logger.info("接收的消息"+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
