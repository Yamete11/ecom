package durden.company.inventory.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class CartCheckoutEventDTO {
    private Long userId;
    private Long paymentMethodId;
    private List<CartItemDTO> items;
}
