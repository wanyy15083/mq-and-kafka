package com.frotly.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 生产者
 * Created by Frotly on 2017/3/14.
 */
public class KafkaProducerTest {

    private final Producer<String, String> producer;
    private final static String TOPIC = "frotly";

    private KafkaProducerTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<String, String>(props);
    }

    public void produce() {
        for (int i = 0; i < 1000; i++) {
            producer.send(new ProducerRecord<String, String>(TOPIC, String.valueOf(i), String.valueOf("hello:" + i)));
        }
        producer.close();
    }

    public static void main(String[] args) {
        new KafkaProducerTest().produce();
    }
}
