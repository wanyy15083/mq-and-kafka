package com.frotly.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by Frotly on 2017/4/13.
 */
public class MessageListenerImpl implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        System.out.println(Thread.currentThread().getName() + " consume messages: " + msgs.size());
        for (MessageExt msg : msgs) {
            System.out.println(msg.toString());
            if ("TopicTest1".equals(msg.getTopic())) {
                if (msg.getTags() != null) {
                    if ("TagA".equals(msg.getTags())) {
                        System.out.println(new String(msg.getBody()));
                    } else if ("TagC".equals(msg.getTags())) {
                        System.out.println(new String(msg.getBody()));
                    }
                }
            } else if ("TopicTest2".equals(msg.getTopic())) {
                System.out.println(new String(msg.getBody()));
            }
        }
        System.out.println("getDelayLevelWhenNextConsume=" + context.getDelayLevelWhenNextConsume() + "getMessageQueue=" + context.getMessageQueue().toString() + "getDelayLevelWhenNextConsume=" + context.getDelayLevelWhenNextConsume());
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
