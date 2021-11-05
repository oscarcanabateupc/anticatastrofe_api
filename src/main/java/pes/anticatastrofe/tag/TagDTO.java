package pes.anticatastrofe.tag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TagDTO {
    @Id String name;

    public TagDTO(Tag t) {
        name = t.name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
