package durden.company.cart.DTOs;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
}
