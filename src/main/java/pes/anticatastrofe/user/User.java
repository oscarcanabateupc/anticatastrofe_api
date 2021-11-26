package pes.anticatastrofe.user;

import lombok.Data;
import pes.anticatastrofe.person.Person;

import javax.persistence.*;

@Entity
@Table
@Data
public class User {
    @Id
    private String email;
    private float last_coordinate_x;
    private float last_coordinate_y;


    @OneToOne
    private Person person;
}