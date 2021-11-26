package pes.anticatastrofe.landmark;

import lombok.Data;

@Data
public class LandmarkDTO {
    private int id;
    private float coordinate_x;
    private float coordinate_y;
    private String tag;
    private String creator_email;
    private String title;
    private String description;

    public LandmarkDTO(Landmark l) {
        id = l.getId();
        coordinate_x = l.getCoordinate_x();
        coordinate_y = l.getCoordinate_y();
        tag = l.getTag().getName();
        creator_email = l.getCreator().getEmail();
        title = l.getTitle();
        description = l.getDescription();
    }
}
