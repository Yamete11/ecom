package durden.company.order.repositories;

import durden.company.order.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> findOrderStatusByTitle(String title);
}

