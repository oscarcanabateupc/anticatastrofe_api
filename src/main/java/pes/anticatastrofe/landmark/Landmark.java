package pes.anticatastrofe.landmark;

import lombok.Data;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.tag.Tag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Landmark {
    @Id
    int id;
    float coordinate_x;
    float coordinate_y;
    float coordinate_z;

    @ManyToOne
    Person creator;

    @ManyToOne
    Tag tag;
}
