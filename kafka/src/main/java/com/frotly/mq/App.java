package com.frotly.mq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Frotly on 2017/4/13.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        DefaultMQProducer producer = (DefaultMQProducer) context.getBean("producer");
        for (int i = 0; i < 3; i++) {

            try {
                {
                    Message msg = new Message("TopicTest1",// topic
                            "TagA",// tag
                            "OrderID001",// key
                            ("Hello MetaQA").getBytes());// body
                    SendResult sendResult = producer.send(msg);
                    System.out.println(sendResult);
                }

                {
                    Message msg = new Message("TopicTest1",// topic
                            "TagB",// tag
                            "OrderID0034",// key
                            ("Hello MetaQB").getBytes());// body
                    SendResult sendResult = producer.send(msg);
                    System.out.println(sendResult);
                }

                {
                    Message msg = new Message("TopicTest1",// topic
                            "TagC",// tag
                            "OrderID061",// key
                            ("Hello MetaQC").getBytes());// body
                    SendResult sendResult = producer.send(msg);
                    System.out.println(sendResult);
                }

                {
                    Message msg = new Message("TopicTest2",// topic
                            "TagD",// tag
                            "OrderID099",// key
                            ("Hello MetaQD").getBytes());// body
                    SendResult sendResult = producer.send(msg);
                    System.out.println(sendResult);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
