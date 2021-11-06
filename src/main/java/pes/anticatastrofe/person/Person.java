package pes.anticatastrofe.person;

import lombok.Data;
import pes.anticatastrofe.landmark.Landmark;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Data
public class Person {
    @Id
    String email;
    String name;
    int phone_num;
    String password;

    @OneToMany
    List<Landmark> landmark;
}
