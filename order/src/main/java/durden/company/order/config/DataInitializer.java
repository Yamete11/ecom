package durden.company.order.config;

import durden.company.order.entities.OrderStatus;
import durden.company.order.repositories.OrderStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OrderStatusRepository orderStatusRepository;

    public DataInitializer(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        OrderStatus statusNew = new OrderStatus();
        statusNew.setTitle("New");
        orderStatusRepository.save(statusNew);

        OrderStatus statusPaid = new OrderStatus();
        statusPaid.setTitle("Paid");
        orderStatusRepository.save(statusPaid);

        OrderStatus statusShipped = new OrderStatus();
        statusShipped.setTitle("Shipped");
        orderStatusRepository.save(statusShipped);

        OrderStatus statusCancelled = new OrderStatus();
        statusCancelled.setTitle("Cancelled");
        orderStatusRepository.save(statusCancelled);
    }
}
