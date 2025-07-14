package durden.company.notification.repositories;

import durden.company.notification.entities.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationStatusRepository extends JpaRepository<NotificationStatus, Long> {
    NotificationStatus findByTitle(String title);
}
