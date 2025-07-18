package durden.company.order.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
