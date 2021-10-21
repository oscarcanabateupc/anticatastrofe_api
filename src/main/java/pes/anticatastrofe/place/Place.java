package pes.anticatastrofe.place;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@IdClass(Place.class)
public class Place implements Serializable {
    @Id float coordinate_x;
    @Id float coordinate_y;
    @Id float coordinate_z;

    public Place() {
    }

    public Place(float coordinate_x, float coordinate_y, float coordinate_z) {
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.coordinate_z = coordinate_z;
    }

    public float getCoordinate_x() {
        return coordinate_x;
    }

    public void setCoordinate_x(float coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public float getCoordinate_y() {
        return coordinate_y;
    }

    public void setCoordinate_y(float coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    public float getCoordinate_z() {
        return coordinate_z;
    }

    public void setCoordinate_z(float coordinate_z) {
        this.coordinate_z = coordinate_z;
    }
}
