package durden.company.order.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusDTO {

    private final Long id;

    private final String title;
}
