package durden.company.order.controllers;

import durden.company.order.DTOs.CreateOrderDTO;
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
    public ResponseEntity<List<CreateOrderDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateOrderDTO> getOrder(@PathVariable long id){
        Optional<CreateOrderDTO> optionalOrderDTO = orderService.findOrder(id);
        return optionalOrderDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreateOrderDTO> createOrder(@RequestBody CreateOrderDTO  orderDTO){
        CreateOrderDTO savedOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }
}

