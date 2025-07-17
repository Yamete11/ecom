package durden.company.cart.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartEventDTO {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
