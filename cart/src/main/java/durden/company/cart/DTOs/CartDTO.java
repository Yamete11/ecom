package durden.company.cart.DTOs;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private LocalDateTime createdAt;
    private List<CartItemDTO> cartItems;
}
