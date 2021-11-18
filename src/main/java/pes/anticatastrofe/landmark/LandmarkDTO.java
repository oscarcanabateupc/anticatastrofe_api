package pes.anticatastrofe.landmark;

import lombok.Data;

@Data
public class LandmarkDTO {
    int id;
    float coordinate_x;
    float coordinate_y;
    String tag;
    String creator_email;
    String description;

    public LandmarkDTO(Landmark l) {
        id = l.getId();
        coordinate_x = l.getCoordinate_x();
        coordinate_y = l.getCoordinate_y();
        tag = l.getTag().getName();
        creator_email = l.getCreator().getEmail();
        description = l.getDescription();
    }
}
