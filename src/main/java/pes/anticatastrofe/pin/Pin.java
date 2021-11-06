package pes.anticatastrofe.pin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;


@IdClass(CompositeKeyPin.class)
@Entity
@Table
public class Pin implements Serializable {
    @Id int landmark_id;
    @Id String email;

    public Pin() {
    }

    public Pin(int landmark_id, String email) {
        this.landmark_id = landmark_id;
        this.email = email;
    }

    public int getLandmark_id() {
        return landmark_id;
    }

    public void setLandmark_id(int landmark_id) {
        this.landmark_id = landmark_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
