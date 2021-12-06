package pes.anticatastrofe.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pes.anticatastrofe.messageWithCoordinates.MessageWithCoordinates;
import pes.anticatastrofe.messageWithCoordinates.MessageWithCoordinatesDTOIn;
import pes.anticatastrofe.person.Person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String content;
    private Date date_sent;
    private Boolean seen;
    @Id
    private int id;

    @OneToOne
    private Person sender;

    @OneToOne
    private Person recipient;

    public Message(MessageDTOIn m, Person s, Person r) {
        content = m.getContent();
        date_sent = m.getDate_sent();
        seen = m.getSeen();
        sender = s;
        recipient = r;
        id = m.getId();
    }

    public Message(MessageWithCoordinatesDTOIn m, Person s, Person r) {
        content = m.getContent();
        date_sent = m.getDate_sent();
        seen = m.getSeen();
        sender = s;
        recipient = r;
        id = m.getId();
    }
}
