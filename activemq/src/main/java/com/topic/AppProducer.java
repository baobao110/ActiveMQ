package com.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * 消息提供者，向消息中间件发送消息  先启动消费者预订 后启动生产者
 */

public class AppProducer {
    //这里的默认端口为61616
    private static final String url="tcp://192.168.0.106:61616";

    private static final String topicName="topic-test";

    private static final Logger logger= LoggerFactory.getLogger(AppProducer.class);

    public static void main(String[] args) throws Exception{

        //1 创建ConnectionFactory
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);

        //2 创建Connection
        Connection connection=connectionFactory.createConnection();

        //3 启动连接
        connection.start();

        //4 建立会话
       Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

       //5 创建消息发送的目的地
        Destination destination=session.createTopic(topicName);

        //6 创建生产者
       MessageProducer messageProducer=session.createProducer(destination);

       for(int i=0;i<100;i++){
           //7 创建消息
           TextMessage textMessage=session.createTextMessage("test"+i);
           messageProducer.send(textMessage);
           logger.info("发送消息"+textMessage.getText());
       }

        // 9 关闭连接
        connection.close();
    }
}
