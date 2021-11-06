package pes.anticatastrofe.area;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Area {
    @Id
    int landmark_id;
}
