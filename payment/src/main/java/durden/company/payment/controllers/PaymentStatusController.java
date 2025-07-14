package durden.company.payment.controllers;

import durden.company.payment.DTOs.PaymentStatusDTO;
import durden.company.payment.services.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment-statuses")
public class PaymentStatusController {

    private final PaymentStatusService paymentStatusService;

    @Autowired
    public PaymentStatusController(PaymentStatusService paymentStatusService) {
        this.paymentStatusService = paymentStatusService;
    }

    @GetMapping
    public List<PaymentStatusDTO> getAll() {
        return paymentStatusService.findAll();
    }
}
