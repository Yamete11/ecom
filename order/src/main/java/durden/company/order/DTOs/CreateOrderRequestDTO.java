package durden.company.order.DTOs;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Data
public class CreateOrderRequestDTO {
    private Long userId;
    private List<CreateOrderItem> items;

    @Getter
    public static class CreateOrderItem {
        private Long productId;
        private Integer quantity;
        private BigDecimal price;
    }
}
