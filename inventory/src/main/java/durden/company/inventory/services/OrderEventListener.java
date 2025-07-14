package durden.company.inventory.services;
import durden.company.inventory.shared.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {

    @KafkaListener(topics = "order-created", groupId = "inventory-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println("Received order: " + event.getOrderId());

        event.getItems().forEach(item -> {
            System.out.println("Decrease stock for product " + item.getProductId() +
                    " by quantity " + item.getQuantity());
        });
    }
}
