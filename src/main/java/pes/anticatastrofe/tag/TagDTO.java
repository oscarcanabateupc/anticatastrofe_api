package pes.anticatastrofe.tag;

import lombok.Data;

@Data
public class TagDTO {
    String name;
    String description;

    public TagDTO(Tag t) {
        name = t.getName();
        description = t.getDescription();
    }
}
