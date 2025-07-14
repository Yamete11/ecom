package durden.company.order.services;

import durden.company.order.entities.OrderItem;
import durden.company.order.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
