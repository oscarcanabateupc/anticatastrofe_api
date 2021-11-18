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
    private String content;
    private Date date_sent;
    private Boolean seen;
    @Id
    private int id;

    @OneToOne
    private Person sender;

    @OneToOne
    private Person recipient;
}
