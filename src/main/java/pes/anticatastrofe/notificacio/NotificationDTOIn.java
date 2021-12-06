package pes.anticatastrofe.notificacio;

import lombok.Data;

@Data
public class NotificationDTOIn {
    private int id;
    private int landmark_id;
    private String tag;
    private String description;
}
