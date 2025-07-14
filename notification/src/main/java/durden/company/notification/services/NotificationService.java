package durden.company.notification.services;

import durden.company.notification.DTOs.NotificationDTO;
import durden.company.notification.entities.Notification;
import durden.company.notification.entities.NotificationStatus;
import durden.company.notification.mapper.NotificationMapper;
import durden.company.notification.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationStatusService notificationStatusService;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, NotificationStatusService notificationStatusService) {
        this.notificationRepository = notificationRepository;
        this.notificationStatusService = notificationStatusService;
    }

    public NotificationDTO sendNotification(NotificationDTO notificationDTO) {
        NotificationStatus status = notificationStatusService.findByTitle("Sent");

        Notification notification = new Notification();
        notification.setToEmail(notificationDTO.getToEmail());
        notification.setSubject(notificationDTO.getSubject());
        notification.setBody(notificationDTO.getBody());
        notification.setCreatedAt(LocalDateTime.now());
        notification.setNotificationStatus(status);

        Notification saved = notificationRepository.save(notification);
        return NotificationMapper.toDto(saved);
    }


}
