package durden.company.notification.mapper;

import durden.company.notification.DTOs.NotificationDTO;
import durden.company.notification.entities.Notification;

public class NotificationMapper {

    public static NotificationDTO toDto(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setToEmail(notification.getToEmail());
        dto.setSubject(notification.getSubject());
        dto.setBody(notification.getBody());
        dto.setStatus(notification.getNotificationStatus().getTitle());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;
    }
}
