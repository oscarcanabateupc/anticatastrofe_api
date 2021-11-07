package pes.anticatastrofe.landmark;

import lombok.Data;

@Data
public class LandmarkDTO {
    int id;
    float coordinate_x;
    float coordinate_y;
    float coordinate_z;
    String tag;
    String creator_email;

    public LandmarkDTO(Landmark l) {
        id = l.id;
        coordinate_x = l.coordinate_x;
        coordinate_y = l.coordinate_y;
        coordinate_z = l.coordinate_z;
        tag = l.tag.getName();
        creator_email = l.creator.getEmail();
    }
}
