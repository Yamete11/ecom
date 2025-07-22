package durden.company.order.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private String category;
}
