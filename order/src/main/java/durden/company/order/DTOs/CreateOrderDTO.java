package durden.company.order.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateOrderDTO {
    private Long userId;
    private BigDecimal totalPrice;
    private List<OrderItemDTO> items;
    private String paymentMethod;
}
