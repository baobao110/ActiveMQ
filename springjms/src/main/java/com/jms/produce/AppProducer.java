package com.jms.produce;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppProducer {
    public static void main(String[] args) {
            ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("produce.xml");
        ProduceService produceService=(ProduceService)applicationContext.getBean("produceServiceImple");
       for(int i=0;i<100;i++){
           produceService.sendMessage("produce");
       }
       applicationContext.close();
    }
}
