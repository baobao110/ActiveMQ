package com.topic;

import com.queue.AppProducer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * 消息的接收者从中间消息件,主题模式两个消费者都全部获取,
 */

public class AppConsumer {
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

        // 6 创建消费者
        MessageConsumer messageConsumer=session.createConsumer(destination);

        // 7 创建监听器
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("接收的消息"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
