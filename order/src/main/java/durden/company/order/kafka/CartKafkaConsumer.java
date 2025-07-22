package durden.company.order.kafka;

import durden.company.order.DTOs.CartCheckoutEventDTO;
import durden.company.order.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CartKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CartKafkaConsumer.class);
    private final OrderService orderService;

    public CartKafkaConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "create-order", groupId = "order-group", containerFactory = "cartCheckoutKafkaListenerContainerFactory")
    public void consumeCartCheckoutEvent(CartCheckoutEventDTO event) {
        logger.info("[Kafka] Received cart checkout event: {}", event);
        orderService.createOrder(event);
    }
}
