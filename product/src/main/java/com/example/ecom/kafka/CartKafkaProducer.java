package com.example.ecom.kafka;

import com.example.ecom.DTOs.AddToCartEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class CartKafkaProducer {

    private final KafkaTemplate<String, AddToCartEventDTO> kafkaTemplate;

    @Value("${kafka.topics.cart-add}")
    private String topic;

    public CartKafkaProducer(KafkaTemplate<String, AddToCartEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAddToCartEvent(AddToCartEventDTO event) {
        kafkaTemplate.send(topic, event);
    }
}
