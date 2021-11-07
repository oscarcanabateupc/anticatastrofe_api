package pes.anticatastrofe.notificacio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotification() {
        return notificationRepository.findAll();
    }

    public Notification addNewNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(int id) {
        notificationRepository.deleteById(id);
    }

    public Optional<Notification> findByID(int id) {
        return notificationRepository.findById(id);
    }
}
