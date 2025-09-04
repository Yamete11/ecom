package durden.company.cart.kafka;

import durden.company.cart.services.IdempotencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import durden.company.cart.DTOs.AddToCartEventDTO;
import durden.company.cart.services.CartService;

@Service
public class CartKafkaConsumer {

    private final CartService cartService;
    private final IdempotencyService idempotencyService;

    @Autowired
    public CartKafkaConsumer(CartService cartService, IdempotencyService idempotencyService) {
        this.cartService = cartService;
        this.idempotencyService = idempotencyService;
    }

    @KafkaListener(topics = "${kafka.topics.cart-add}", groupId = "cart-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeAddToCartEvent(AddToCartEventDTO event) {
        if (idempotencyService.isProcessed(event.getEventId())) {
            return;
        }
        cartService.addToCart(event);

        idempotencyService.markProcessed(event.getEventId());
    }

}
