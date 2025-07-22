package durden.company.order.services;

import durden.company.order.DTOs.CartItemDTO;
import durden.company.order.DTOs.OrderItemDTO;
import durden.company.order.entities.OrderItem;
import durden.company.order.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItemDTO> getOrderItemsByOrderId(Long id){
        List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderId(id);

        return orderItems.stream()
                .map(orderItem -> new OrderItemDTO(orderItem.getProductId(), orderItem.getQuantity(), orderItem.getPrice()))
                .toList();
    }
}
