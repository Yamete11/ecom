package durden.company.payment.configs;

import durden.company.payment.entites.PaymentMethod;
import durden.company.payment.entites.PaymentStatus;
import durden.company.payment.repositories.PaymentMethodRepository;
import durden.company.payment.repositories.PaymentStatusRepository;
import org.springframework.boot.CommandLineRunner;

public class DataInitializer implements CommandLineRunner {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentStatusRepository paymentStatusRepository;

    public DataInitializer(PaymentMethodRepository paymentMethodRepository, PaymentStatusRepository paymentStatusRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        PaymentMethod creditCard = new PaymentMethod();
        creditCard.setTitle("Card");
        paymentMethodRepository.save(creditCard);

        PaymentMethod paypal = new PaymentMethod();
        paypal.setTitle("Cash");
        paymentMethodRepository.save(paypal);


        PaymentStatus pending = new PaymentStatus();
        pending.setTitle("Pending");
        paymentStatusRepository.save(pending);

        PaymentStatus completed = new PaymentStatus();
        completed.setTitle("Paid");
        paymentStatusRepository.save(completed);

        PaymentStatus failed = new PaymentStatus();
        failed.setTitle("Failed");
        paymentStatusRepository.save(failed);

    }
}
