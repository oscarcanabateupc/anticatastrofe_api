package pes.anticatastrofe.user;

import lombok.Data;
import pes.anticatastrofe.person.Person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    
    @OneToOne
    Person person;
}