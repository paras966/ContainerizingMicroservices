package com.spring.API.controller;

import com.spring.API.dao.RouterDataDao;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class KafkaRoutersConsumer {
    @PostConstruct
    public void startConsumer() {
        new Thread(this::consumeMessages).start();
    }
    public void consumeMessages() {
        String KAFKA_SERVER = System.getenv("KAFKA_SERVER");
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER+":9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "routers");

        try (KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties)) {

            kafkaConsumer.subscribe(Collections.singleton("routersData"));

            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(2000)); // Poll for records
                // Process the records
                records.forEach(record -> {
                    RouterDataDao routerDataDao = new RouterDataDao();
                    routerDataDao.save(record);
                });
            }
        }
    }

}
