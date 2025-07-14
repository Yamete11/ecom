package durden.company.order.services;

import durden.company.order.DTOs.CreateOrderRequestDTO;
import durden.company.order.DTOs.OrderDTO;
import durden.company.order.entities.Order;
import durden.company.order.entities.OrderItem;
import durden.company.order.entities.OrderStatus;
import durden.company.order.mappers.OrderMapper;
import durden.company.order.repositories.OrderRepository;
import durden.company.order.shared.events.OrderCreatedEvent;
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
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderStatusService orderStatusService, OrderItemService orderItemService, KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.orderStatusService = orderStatusService;
        this.orderItemService = orderItemService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDTO)
                .toList();
    }

    public Optional<OrderDTO> findOrder(long id) {
        return orderRepository.findById(id)
                .map(OrderMapper::toDTO);
    }

    public OrderDTO createOrder(CreateOrderRequestDTO orderDTO) {
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setCreatedAt(LocalDateTime.now());

        OrderStatus orderStatus = orderStatusService.findOrderStatusById(1L);
        order.setOrderStatus(orderStatus);

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> list = new ArrayList<>();

        for (CreateOrderRequestDTO.CreateOrderItem itemDTO : orderDTO.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(itemDTO.getProductId());
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(itemDTO.getPrice());

            BigDecimal lineTotal = itemDTO.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            totalPrice = totalPrice.add(lineTotal);

            list.add(item);
        }

        order.setItems(list);
        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getTotalPrice(),
                savedOrder.getCreatedAt(),
                savedOrder.getItems().stream()
                        .map(i -> new OrderCreatedEvent.OrderItemEvent(
                                i.getProductId(),
                                i.getQuantity(),
                                i.getPrice()))
                        .toList()
        );

        kafkaTemplate.send("order-created", event);

        return toDTO(savedOrder);
    }


}

