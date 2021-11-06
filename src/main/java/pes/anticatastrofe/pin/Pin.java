package pes.anticatastrofe.pin;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table
@Data
@IdClass(CompositeKeyPin.class)
public class Pin implements Serializable {
    @Id
    int landmark_id;
    @Id
    String email;
}
