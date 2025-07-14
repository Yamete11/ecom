package durden.company.notification.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {

    private String toEmail;
    private String subject;
    private String body;
    private String status;
    private LocalDateTime createdAt;
}
