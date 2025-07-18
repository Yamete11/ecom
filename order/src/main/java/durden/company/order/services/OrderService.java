package durden.company.order.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import durden.company.order.DTOs.CreateOrderDTO;
import durden.company.order.entities.Order;
import durden.company.order.entities.OrderItem;
import durden.company.order.entities.OrderStatus;
import durden.company.order.events.OrderCreatedEvent;
import durden.company.order.mappers.OrderMapper;
import durden.company.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static durden.company.order.mappers.OrderMapper.toDTO;

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

    public List<CreateOrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDTO)
                .toList();
    }

    public Optional<CreateOrderDTO> findOrder(long id) {
        return orderRepository.findById(id)
                .map(OrderMapper::toDTO);
    }

    public CreateOrderDTO createOrder(CreateOrderDTO orderDTO) {
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

        Order savedOrder = orderRepository.save(order);

        var event = new OrderCreatedEvent(savedOrder.getId(), savedOrder.getUserId(),
                savedOrder.getTotalPrice(), orderDTO.getPaymentMethod());

        try {
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("order-created-topic", eventJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize order created event", e);
        }

        return OrderMapper.toDTO(savedOrder);

    }


}

