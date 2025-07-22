package durden.company.inventory.kafka;

import durden.company.inventory.DTOs.CartCheckoutEventDTO;
import durden.company.inventory.services.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(InventoryKafkaConsumer.class);
    private final WarehouseService warehouseService;

    public InventoryKafkaConsumer(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @KafkaListener(topics = "inventory-check", groupId = "inventory-group", containerFactory = "cartCheckoutKafkaListenerContainerFactory")
    public void consumeCartCheckoutEvent(CartCheckoutEventDTO event) {
        logger.info("[Kafka] Received cart checkout event: {}", event);
        warehouseService.postCheckout(event);
    }
}
