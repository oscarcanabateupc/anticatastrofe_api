package pes.anticatastrofe.landmark;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Landmark {
    @Id int id;
    String tag;
    float coordinate_x;
    float coordinate_y;
    float coordinate_z;

    public Landmark() {
    }

    public Landmark(int id, String tag, float coordinate_x, float coordinate_y, float coordinate_z) {
        this.id = id;
        this.tag = tag;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.coordinate_z = coordinate_z;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
