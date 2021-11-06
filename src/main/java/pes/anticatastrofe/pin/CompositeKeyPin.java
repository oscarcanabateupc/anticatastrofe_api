package pes.anticatastrofe.pin;

import java.io.Serializable;

public class CompositeKeyPin implements Serializable {
    int landmark_id;
    String email;

    public CompositeKeyPin(){};

    public CompositeKeyPin(Integer landmark_id, String email) {
        this.landmark_id = landmark_id;
        this.email = email;
    }
}
