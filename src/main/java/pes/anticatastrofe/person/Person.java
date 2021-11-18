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
    private String email;
    private String name;
    private int phone_num;
    private String password;
    private String token;

    @OneToMany
    private List<Landmark> landmark;
}
