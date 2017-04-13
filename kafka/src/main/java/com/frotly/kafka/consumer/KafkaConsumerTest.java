package com.frotly.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;

/**
 * 消费者
 * Created by Frotly on 2017/3/14.
 */
public class KafkaConsumerTest {
    private final Consumer consumer;
    private final static String TOPIC = "frotly";

    private KafkaConsumerTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<String, String>(props);
    }

    public void consume() {
        consumer.subscribe(Arrays.asList("frotly"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(200);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset=%d,key=%s,value=%s%n", record.offset(), record.key(), record.value());
            }
        }
    }

    public static void main(String[] args) {
        new KafkaConsumerTest().consume();
    }
}
