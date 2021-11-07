package pes.anticatastrofe.tag;

import lombok.Data;

@Data
public class TagDTO {
    String name;

    public TagDTO(Tag t) {
        name = t.name;
    }
}
