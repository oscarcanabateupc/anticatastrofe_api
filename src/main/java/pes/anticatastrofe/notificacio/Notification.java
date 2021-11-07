package pes.anticatastrofe.notificacio;

import lombok.Data;
import pes.anticatastrofe.landmark.Landmark;
import pes.anticatastrofe.tag.Tag;

import javax.persistence.*;

@Entity
@Table
@Data
public class Notification {
    @Id
    int id;

    @ManyToOne
    Tag tag;

    @OneToOne
    Landmark landmark;
}
