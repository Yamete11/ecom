package durden.company.notification.services;

import durden.company.notification.DTOs.NotificationStatusDTO;
import durden.company.notification.entities.Notification;
import durden.company.notification.entities.NotificationStatus;
import durden.company.notification.repositories.NotificationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationStatusService {

    private final NotificationStatusRepository notificationStatusRepository;

    @Autowired
    public NotificationStatusService(NotificationStatusRepository notificationStatusRepository) {
        this.notificationStatusRepository = notificationStatusRepository;
    }

    public List<NotificationStatusDTO> findAllStatuses() {
        return notificationStatusRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public NotificationStatus findByTitle(String title) {
        return notificationStatusRepository.findByTitle(title);
    }

    private NotificationStatusDTO toDTO(NotificationStatus notificationStatus) {
        return new NotificationStatusDTO(
                notificationStatus.getId(),
                notificationStatus.getTitle()
        );
    }
}
