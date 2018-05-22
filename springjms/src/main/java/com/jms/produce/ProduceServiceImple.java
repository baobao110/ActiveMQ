package com.jms.produce;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;

public class ProduceServiceImple implements ProduceService {

    @Autowired
    private JmsTemplate jmsTemplate;

   /* //队列模式下的目的地
    @Resource(name="queueDestination")
    private Destination destination;*/

   //主题模式下的目的地
   @Resource(name="topicDestionation")
    private Destination destination;

    private static final Logger logger= LoggerFactory.getLogger(ProduceServiceImple.class);

    public void sendMessage(final String message) {
        //使用jmsTemplate发送消息
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                //创建消息
                TextMessage textMessage=session.createTextMessage(message);
               logger.info("发送信息"+textMessage.getText());
                return textMessage;
            }
        });
    }
}
