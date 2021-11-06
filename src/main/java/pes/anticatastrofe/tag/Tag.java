package pes.anticatastrofe.tag;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Tag {
    @Id
    String name;
}
