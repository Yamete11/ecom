package durden.company.inventory.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private String category;
}
