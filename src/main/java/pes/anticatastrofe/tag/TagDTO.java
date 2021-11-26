package pes.anticatastrofe.tag;

import lombok.Data;

@Data
public class TagDTO {
    private String name;
    private String description;
    private float color;

    public TagDTO(Tag t) {
        name = t.getName();
        description = t.getDescription();
        color = t.getColor();
    }
}
