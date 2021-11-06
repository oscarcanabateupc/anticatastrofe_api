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
    int id;

    @OneToOne
    Message message;

    @OneToOne
    Landmark landmark;
    
}
