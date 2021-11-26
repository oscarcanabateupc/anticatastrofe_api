package pes.anticatastrofe.landmark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.tag.Tag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Landmark {
    @Id
    private int id;
    private float coordinate_x;
    private float coordinate_y;
    private String title;
    private String description;

    @ManyToOne
    private Person creator;

    @ManyToOne
    private Tag tag;
}
