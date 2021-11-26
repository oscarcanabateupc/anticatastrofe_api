package pes.anticatastrofe.notificacio;

import lombok.Data;

@Data
public class NotificationDTO {
    private int id;
    private int landmark_id;
    private String tag;
    private String description;

    NotificationDTO(Notification n) {
        id = n.getId();
        landmark_id = n.getLandmark().getId();
        if (n.getTag() != null) {
            tag = n.getTag().getName();
        }
        description = n.getDescription();
    }
}
