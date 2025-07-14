package durden.company.order.mappers;

import durden.company.order.DTOs.OrderStatusDTO;
import durden.company.order.entities.OrderStatus;

public class OrderStatusMapper {

    public static OrderStatusDTO toDTO(OrderStatus orderStatus) {
        return new OrderStatusDTO(
                orderStatus.getId(),
                orderStatus.getTitle()
        );
    }
}
