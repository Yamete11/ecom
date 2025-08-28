package durden.company.cart.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import durden.company.cart.DTOs.AddToCartEventDTO;
import durden.company.cart.services.CartService;

@Service
public class CartKafkaConsumer {

    private final CartService cartService;

    @Autowired
    public CartKafkaConsumer(CartService cartService) {
        this.cartService = cartService;
    }

    @KafkaListener(topics = "${kafka.topics.cart-add}", groupId = "cart-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeAddToCartEvent(AddToCartEventDTO event) {
        cartService.addToCart(event);
    }

}
