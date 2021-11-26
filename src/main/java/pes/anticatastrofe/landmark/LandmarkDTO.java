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
    private boolean is_area;
    private float radius;

    public LandmarkDTO(Landmark l) {
        id = l.getId();
        coordinate_x = l.getCoordinate_x();
        coordinate_y = l.getCoordinate_y();
        if (l.getTag() != null) {
            tag = l.getTag().getName();
        }
        if (l.getCreator() != null) {
            creator_email = l.getCreator().getEmail();
        }
        title = l.getTitle();
        description = l.getDescription();
        is_area = l.is_area();
        radius = getRadius();
    }
}
