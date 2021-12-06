package pes.anticatastrofe.messageWithCoordinates;

import lombok.Data;

import java.util.Date;

@Data
public class MessageWithCoordinatesDTOIn {
    private String content;
    private Date date_sent;
    private Boolean seen;
    private int id;
    private String sender_email;
    private String recipient_email;
    private int landmark_id;
    private String tag;
    private String creator_email;
}
