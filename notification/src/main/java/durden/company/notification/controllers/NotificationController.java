package durden.company.notification.controllers;

import durden.company.notification.DTOs.NotificationDTO;
import durden.company.notification.entities.Notification;
import durden.company.notification.entities.NotificationStatus;
import durden.company.notification.services.NotificationService;
import durden.company.notification.services.NotificationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> sendNotification(@RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(notificationService.sendNotification(dto));
    }
}

