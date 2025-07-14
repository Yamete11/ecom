package durden.company.notification.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String toEmail;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "notification_status_id", nullable = false)
    @JsonManagedReference
    private NotificationStatus notificationStatus;
}
