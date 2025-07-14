package durden.company.notification.controllers;

import durden.company.notification.DTOs.NotificationStatusDTO;
import durden.company.notification.entities.NotificationStatus;
import durden.company.notification.services.NotificationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification-statuses")
public class NotificationStatusController {

    private final NotificationStatusService notificationStatusService;

    @Autowired
    public NotificationStatusController(NotificationStatusService notificationStatusService) {
        this.notificationStatusService = notificationStatusService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationStatusDTO>> getAllStatuses() {
        return ResponseEntity.ok(notificationStatusService.findAllStatuses());

    }
}

