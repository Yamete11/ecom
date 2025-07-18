package durden.company.payment.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePaymentDTO {
    private Long orderId;
    private BigDecimal sum;
    private Long paymentMethodId;
}