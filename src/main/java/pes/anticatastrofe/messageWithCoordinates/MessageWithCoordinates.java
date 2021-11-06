package pes.anticatastrofe.messageWithCoordinates;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class MessageWithCoordinates {
    @Id
    int message_id;
    float coordinate_x;
    float coordinate_y;
    float coordinate_z;
}
