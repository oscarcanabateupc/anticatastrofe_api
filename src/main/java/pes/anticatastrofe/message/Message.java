package pes.anticatastrofe.message;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
public class Message {
    String content;
    Date date_sent;
    Boolean read;
    @Id
    int id;
    String recipient_mail;
}
