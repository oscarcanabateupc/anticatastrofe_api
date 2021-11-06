package pes.anticatastrofe.area;

import lombok.Data;
import pes.anticatastrofe.landmark.Landmark;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Area {
    @Id
    int id;

    @OneToMany
    List<Landmark> landmarks;

    @OneToOne
    Landmark center;
}
