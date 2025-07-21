package durden.company.cart.DTOs;

import lombok.Data;

@Data
public class CartCheckoutRequestDTO {
    private Long userId;
    private Long paymentMethodId;
}
