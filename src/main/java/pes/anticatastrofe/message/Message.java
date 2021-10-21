package pes.anticatastrofe.message;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Message {
    String content;
    Date date_sent;
    Boolean read;
    @Id int id;
    String recipient_mail;

    public Message() {
    }

    public Message(String content, Date date_sent, Boolean read, int id, String recipient_mail, String sender_mail) {
        this.content = content;
        this.date_sent = date_sent;
        this.read = read;
        this.id = id;
        this.recipient_mail = recipient_mail;
        this.sender_mail = sender_mail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(Date date_sent) {
        this.date_sent = date_sent;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipient_mail() {
        return recipient_mail;
    }

    public void setRecipient_mail(String recipient_mail) {
        this.recipient_mail = recipient_mail;
    }

    public String getSender_mail() {
        return sender_mail;
    }

    public void setSender_mail(String sender_mail) {
        this.sender_mail = sender_mail;
    }

    String sender_mail;
}
