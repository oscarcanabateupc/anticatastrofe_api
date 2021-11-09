package pes.anticatastrofe.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pes.anticatastrofe.landmark.Landmark;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    String email;
    String name;
    int phone_num;
    String password;
    String token;

    @OneToMany
    List<Landmark> landmark;
}
