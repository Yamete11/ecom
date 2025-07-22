package durden.company.order.controllers;

import durden.company.order.DTOs.*;
import durden.company.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }


    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CartCheckoutEventDTO cartCheckoutEventDTO) {
        orderService.createOrder(cartCheckoutEventDTO);
        return ResponseEntity.noContent().build();
    }
}

