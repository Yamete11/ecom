package durden.company.order.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderDTO {

    private final Long id;
    private final Long userId;
    private final BigDecimal totalPrice;
    private final LocalDateTime createdAt;
    private final String orderStatusTitle;
}
