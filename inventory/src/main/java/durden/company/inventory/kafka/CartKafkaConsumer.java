package durden.company.inventory.kafka;

import durden.company.inventory.DTOs.CartCheckoutEventDTO;
import durden.company.inventory.services.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CartKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CartKafkaConsumer.class);
    private final WarehouseService warehouseService;

    public CartKafkaConsumer(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @KafkaListener(topics = "order-create", groupId = "inventory-group", containerFactory = "cartCheckoutKafkaListenerContainerFactory")
    public void consumeCartCheckoutEvent(CartCheckoutEventDTO event) {
        logger.info("[Kafka] Received cart checkout event: {}", event);
        warehouseService.postCheckout(event);
    }
}
