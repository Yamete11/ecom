package durden.company.order.DTOs;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long userId;
    private LocalDateTime createdAt;
    private String orderStatus;
    private BigDecimal totalPrice;
    private List<OrderItemDTO> items;
}
