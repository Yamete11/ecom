package durden.company.notification.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notification_statuses")
@Data
public class NotificationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "notificationStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Notification> orders = new ArrayList<>();
}
