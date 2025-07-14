package durden.company.order.mappers;

import durden.company.order.DTOs.OrderDTO;
import durden.company.order.entities.Order;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getUserId(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getOrderStatus().getTitle()
        );
    }
}
