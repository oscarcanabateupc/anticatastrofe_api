package pes.anticatastrofe.landmark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.person.PersonRepository;
import pes.anticatastrofe.tag.Tag;
import pes.anticatastrofe.tag.TagRepository;

import javax.persistence.*;
import java.util.Optional;

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
    private boolean is_area;
    private float radius;

    @ManyToOne
    private Person creator;

    @ManyToOne
    private Tag tag;

    @Autowired
    public Landmark(LandMarkDTOin l, Person p, Tag t){
        id = l.getId();
        coordinate_x = l.getCoordinate_x();
        coordinate_y = l.getCoordinate_y();
        title = l.getTitle();
        description = l.getDescription();
        creator = p;
        tag = t;
    }

}
