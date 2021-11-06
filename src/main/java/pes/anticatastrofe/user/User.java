package pes.anticatastrofe.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class User {
    @Id
    String email;
    float last_coordinate_x;
    float last_coordinate_y;
    float last_coordinate_z;
}