package durden.company.inventory.kafka;

import durden.company.inventory.DTOs.CartCheckoutEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryKafkaProducer {

    private final KafkaTemplate<String, CartCheckoutEventDTO> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(InventoryKafkaProducer.class);


    @Autowired
    public InventoryKafkaProducer(KafkaTemplate<String, CartCheckoutEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCartCheckoutEvent(CartCheckoutEventDTO event) {
        kafkaTemplate.send("create-order", event);
        logger.info("[Kafka] Sent cart checkout event: {}", event);
    }
}
