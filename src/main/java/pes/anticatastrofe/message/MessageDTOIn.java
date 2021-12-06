package pes.anticatastrofe.message;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTOIn {
    private String content;
    private Date date_sent;
    private Boolean seen;
    private int id;
    private String sender_email;
    private String recipient_email;
}
