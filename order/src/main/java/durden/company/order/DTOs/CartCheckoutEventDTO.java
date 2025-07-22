package durden.company.order.DTOs;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartCheckoutEventDTO {
    private Long userId;
    private Long paymentMethodId;
    private BigDecimal totalPrice;
    private List<CartItemDTO> items;
}
