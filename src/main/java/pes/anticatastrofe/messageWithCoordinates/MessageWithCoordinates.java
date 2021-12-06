package pes.anticatastrofe.messageWithCoordinates;

import lombok.Data;
import pes.anticatastrofe.landmark.Landmark;
import pes.anticatastrofe.message.Message;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
public class MessageWithCoordinates {
    @Id
    private int id;

    @OneToOne
    private Message message;

    @OneToOne
    private Landmark landmark;

    public MessageWithCoordinates(MessageWithCoordinatesDTOIn m, Message m2,Landmark l)
    {
        id = m.getId();
        message = m2;
        landmark = l;
    }
}
