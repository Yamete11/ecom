package durden.company.payment.services;

import durden.company.payment.DTOs.PaymentStatusDTO;
import durden.company.payment.entites.PaymentStatus;
import durden.company.payment.mappers.PaymentStatusMapper;
import durden.company.payment.repositories.PaymentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepository;

    @Autowired
    public PaymentStatusService(PaymentStatusRepository paymentStatusRepository) {
        this.paymentStatusRepository = paymentStatusRepository;
    }

    public List<PaymentStatusDTO> findAll() {
        return PaymentStatusMapper.toDTOList(paymentStatusRepository.findAll());
    }
}
