package pes.anticatastrofe.notificacio;

import lombok.Data;

@Data
public class NotificationDTO {
    int id;
    int landmark_id;
    String tag;
    String description;

    NotificationDTO(Notification n) {
        id = n.getId();
        landmark_id = n.getLandmark().getId();
        tag = n.getTag().getName();
        description = n.getDescription();
    }
}
