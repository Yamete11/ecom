package durden.company.order.services;

import durden.company.order.DTOs.OrderStatusDTO;
import durden.company.order.entities.OrderStatus;
import durden.company.order.mappers.OrderStatusMapper;
import durden.company.order.repositories.OrderStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public List<OrderStatusDTO> findAllOrderStatuses() {
        return orderStatusRepository.findAll().stream()
                .map(OrderStatusMapper::toDTO)
               .toList();
    }

    public OrderStatus findOrderStatusByName(String title) {
        return orderStatusRepository.findOrderStatusByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("OrderStatus not found: " + title));
    }


}
