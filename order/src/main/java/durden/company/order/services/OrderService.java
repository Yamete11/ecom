package durden.company.order.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import durden.company.order.DTOs.CartCheckoutEventDTO;
import durden.company.order.DTOs.OrderDTO;
import durden.company.order.entities.Order;
import durden.company.order.entities.OrderItem;
import durden.company.order.entities.OrderStatus;
import durden.company.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;
    private final OrderItemService orderItemService;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    public OrderService(OrderRepository orderRepository, OrderStatusService orderStatusService, OrderItemService orderItemService, ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.orderStatusService = orderStatusService;
        this.orderItemService = orderItemService;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<OrderDTO> getOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
               .map(order -> {
                   OrderDTO orderDTO = new OrderDTO();
                   orderDTO.setUserId(order.getUserId());
                   orderDTO.setTotalPrice(order.getTotalPrice());
                   orderDTO.setCreatedAt(order.getCreatedAt());
                   orderDTO.setOrderStatus(order.getOrderStatus().getTitle());
                   orderDTO.setItems(orderItemService.getOrderItemsByOrderId(order.getId()));
                    return orderDTO;
                })
               .toList();
    }


    public void createOrder(CartCheckoutEventDTO orderDTO) {
        OrderStatus newStatus = orderStatusService.findOrderStatusByName("New");
        if (newStatus == null) {
            throw new RuntimeException("OrderStatus 'new' not found");
        }

        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderStatus(newStatus);

        for (var itemDto : orderDTO.getItems()) {
            OrderItem item = new OrderItem();
            item.setProductId(itemDto.getProductId());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());
            item.setOrder(order);
            order.getItems().add(item);
        }

        orderRepository.save(order);
    }


}

