package pes.anticatastrofe.landmark;

public class LandmarkDTO {
    int id;
    String tag;
    float coordinate_x;
    float coordinate_y;
    float coordinate_z;

    public LandmarkDTO(Landmark l) {
        id = l.id;
        tag = l.tag;
        coordinate_x = l.coordinate_x;
        coordinate_y = l.coordinate_y;
        coordinate_z = l.coordinate_z;
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
