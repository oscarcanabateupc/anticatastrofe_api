package pes.anticatastrofe.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pes.anticatastrofe.person.Person;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String email;
    private float last_coordinate_x;
    private float last_coordinate_y;

    @OneToOne
    private Person person;

    public User(UserDTOIn u, Person p){
        email = u.getEmail();
        last_coordinate_x = u.getLast_coordinate_x();
        last_coordinate_y = u.getLast_coordinate_y();
        person = p;
    }
}