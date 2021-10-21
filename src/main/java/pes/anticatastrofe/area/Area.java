package pes.anticatastrofe.area;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Area {
    @Id int landmark_id;

    public Area() {
    }

    public Area(int landmark_id) {
        this.landmark_id = landmark_id;
    }

    public int getLandmark_id() {
        return landmark_id;
    }

    public void setLandmark_id(int landmark_id) {
        this.landmark_id = landmark_id;
    }
}
