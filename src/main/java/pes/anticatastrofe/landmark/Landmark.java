package pes.anticatastrofe.landmark;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Landmark {
    @Id
    int id;
    String tag;
    float coordinate_x;
    float coordinate_y;
    float coordinate_z;
}
