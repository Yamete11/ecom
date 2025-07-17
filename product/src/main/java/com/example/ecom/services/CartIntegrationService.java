package com.example.ecom.services;

import com.example.ecom.DTOs.AddToCartEventDTO;
import com.example.ecom.kafka.CartKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartIntegrationService {

    private final CartKafkaProducer cartKafkaProducer;

    @Autowired
    public CartIntegrationService(CartKafkaProducer cartKafkaProducer) {
        this.cartKafkaProducer = cartKafkaProducer;
    }

    public void addProductToCart(Long userId, Long productId, Integer quantity) {
        AddToCartEventDTO event = new AddToCartEventDTO(userId, productId, quantity);
        cartKafkaProducer.sendAddToCartEvent(event);
    }
}
