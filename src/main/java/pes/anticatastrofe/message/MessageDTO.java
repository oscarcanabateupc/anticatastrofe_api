package pes.anticatastrofe.message;

import lombok.Data;
import pes.anticatastrofe.person.Person;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
public class MessageDTO {
    private String content;
    private Date date_sent;
    private Boolean seen;
    private int id;
    private String sender_email;
    private String recipient_email;

    public MessageDTO(Message m)
    {
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
}
