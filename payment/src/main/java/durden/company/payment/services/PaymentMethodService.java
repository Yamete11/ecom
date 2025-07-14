package durden.company.payment.services;

import durden.company.payment.DTOs.PaymentMethodDTO;
import durden.company.payment.mappers.PaymentMethodMapper;
import durden.company.payment.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<PaymentMethodDTO> findAll() {
        return PaymentMethodMapper.toDTOList(paymentMethodRepository.findAll());
    }
}
