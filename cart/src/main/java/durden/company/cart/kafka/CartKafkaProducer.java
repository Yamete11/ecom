package durden.company.cart.kafka;

import durden.company.cart.DTOs.CartCheckoutEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartKafkaProducer {

    private final KafkaTemplate<String, CartCheckoutEventDTO> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CartKafkaProducer.class);

    @Autowired
    public CartKafkaProducer(KafkaTemplate<String, CartCheckoutEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCartCheckoutEvent(CartCheckoutEventDTO event) {
        kafkaTemplate.send("inventory-check", event);
        logger.info("[Kafka] Sent cart checkout event: {}", event);
    }
}
