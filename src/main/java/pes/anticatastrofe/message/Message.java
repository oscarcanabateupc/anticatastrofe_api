package pes.anticatastrofe.message;

import lombok.Data;
import pes.anticatastrofe.person.Person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
public class Message {
    String content;
    Date date_sent;
    Boolean seen;
    @Id
    int id;

    @OneToOne
    Person sender;

    @OneToOne
    Person recipient;
}
