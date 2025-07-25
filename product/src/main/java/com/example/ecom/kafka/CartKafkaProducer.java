package com.example.ecom.kafka;

import com.example.ecom.DTOs.AddToCartEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class CartKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(CartKafkaProducer.class);

    private final KafkaTemplate<String, AddToCartEventDTO> kafkaTemplate;

    @Value("${kafka.topics.cart-add}")
    private String topic;

    public CartKafkaProducer(KafkaTemplate<String, AddToCartEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAddToCartEvent(AddToCartEventDTO event) {
        logger.info("Sending AddToCartEventDTO to topic {}: {}", topic, event);
        kafkaTemplate.send(topic, event);
    }
}
