package durden.company.order.controllers;

import durden.company.order.DTOs.OrderStatusDTO;
import durden.company.order.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-statuses")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    @Autowired
    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping
    public ResponseEntity<List<OrderStatusDTO>> getAllOrderStatuses() {
        return ResponseEntity.ok(orderStatusService.findAllOrderStatuses());
    }
}
