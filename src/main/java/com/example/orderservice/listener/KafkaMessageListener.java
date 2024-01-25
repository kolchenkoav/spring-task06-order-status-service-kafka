package com.example.orderservice.listener;

import com.example.orderservice.model.Event;
import com.example.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {
    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Value("${app.kafka.kafkaOrderStatusTopic}")
    private String topicName;

    @KafkaListener(topics = "${app.kafka.kafkaOrderTopic}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload Order order) {
        log.info("Received order: {}", order);

        Event event = new Event();
        event.setStatus("CREATED");
        event.setDate(Instant.now());
        kafkaTemplate.send(topicName, event);
    }
}
