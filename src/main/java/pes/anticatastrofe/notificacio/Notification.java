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
    private int id;
    private String description;

    @ManyToOne
    private Tag tag;

    @OneToOne
    private Landmark landmark;

    public Notification(NotificationDTOIn n, Tag t, Landmark l)
    {
        id = n.getLandmark_id();
        description = n.getDescription();
        tag = t;
        landmark = l;
    }
}
