package pes.anticatastrofe.notificacio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pes.anticatastrofe.landmark.Landmark;
import pes.anticatastrofe.tag.Tag;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    int id;

    @ManyToOne
    Tag tag;

    @OneToOne
    Landmark landmark;
}
