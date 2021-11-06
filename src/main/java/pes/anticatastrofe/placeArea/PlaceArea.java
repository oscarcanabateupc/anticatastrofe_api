package pes.anticatastrofe.placeArea;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Table
@Entity
@Data
@IdClass(PlaceArea.class)
public class PlaceArea implements Serializable {
    @Id
    float coordinate_x;
    @Id
    float coordinate_y;
    @Id
    float coordinate_z;
    @Id
    int landmark_id;
}
