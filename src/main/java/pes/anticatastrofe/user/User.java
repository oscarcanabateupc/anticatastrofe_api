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
    private String email;
    private float last_coordinate_x;
    private float last_coordinate_y;

    @OneToOne
    private Person person;
}