package durden.company.notification.config;

import durden.company.notification.entities.NotificationStatus;
import durden.company.notification.repositories.NotificationStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final NotificationStatusRepository notificationStatusRepository;

    public DataInitializer(NotificationStatusRepository notificationStatusRepository) {
        this.notificationStatusRepository = notificationStatusRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        NotificationStatus statusNew = new NotificationStatus();
        statusNew.setTitle("Sent");
        notificationStatusRepository.save(statusNew);

        NotificationStatus statusFailed = new NotificationStatus();
        statusFailed.setTitle("Failed");
        notificationStatusRepository.save(statusNew);
    }
}
