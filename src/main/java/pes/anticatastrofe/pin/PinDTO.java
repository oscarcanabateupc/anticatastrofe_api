package pes.anticatastrofe.pin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class PinDTO {
    int landmark_id;
    String email;

    public PinDTO(Pin p) {
        landmark_id = p.landmark_id;
        email = p.email;
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
