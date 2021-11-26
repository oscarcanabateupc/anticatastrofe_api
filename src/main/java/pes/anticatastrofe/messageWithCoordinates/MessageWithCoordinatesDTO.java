package pes.anticatastrofe.messageWithCoordinates;

import lombok.Data;
import pes.anticatastrofe.landmark.Landmark;
import pes.anticatastrofe.message.Message;

import java.util.Date;

@Data
public class MessageWithCoordinatesDTO {
    private String content;
    private Date date_sent;
    private Boolean seen;
    private int id;
    private String sender_email;
    private String recipient_email;
    private int landmark_id;
    private float coordinate_x;
    private float coordinate_y;
    private String tag;
    private String creator_email;
    private String title;
    private String description;
    private boolean is_area;
    private float radius;

    public MessageWithCoordinatesDTO(MessageWithCoordinates mwc)
    {
        if (mwc.getMessage() != null) {
            Message m = mwc.getMessage();
            content = m.getContent();
            date_sent = m.getDate_sent();
            seen = m.getSeen();
            id = m.getId();
            if (m.getSender() != null) {
                sender_email = m.getSender().getEmail();
            }
            if (m.getRecipient() != null) {
                recipient_email = m.getRecipient().getEmail();
            }
        }
        if(mwc.getLandmark() != null) {
            Landmark l = mwc.getLandmark();
            landmark_id = l.getId();
            coordinate_x = l.getCoordinate_x();
            coordinate_y = l.getCoordinate_y();
            if (l.getTag() != null) {
                tag = l.getTag().getName();
            }
            if (l.getCreator() != null) {
                creator_email = l.getCreator().getEmail();
            }
            title = l.getTitle();
            description = l.getDescription();
            is_area = l.is_area();
            radius = getRadius();
        }
    }
}
