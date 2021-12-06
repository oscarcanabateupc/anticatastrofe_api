package pes.anticatastrofe.landmark;

import lombok.Data;

@Data
public class LandMarkDTOin {
    private int id;
    private float coordinate_x;
    private float coordinate_y;
    private String tag_name;
    private String creator_email;
    private String title;
    private String description;
}
