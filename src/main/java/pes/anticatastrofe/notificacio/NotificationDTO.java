package pes.anticatastrofe.notificacio;

import lombok.Data;

@Data
public class NotificationDTO {
    int id;
    int landmark_id;
    String tag;

    NotificationDTO(Notification n) {
        id = n.id;
        landmark_id = n.landmark.getId();
        tag = n.tag.getName();
    }
}
