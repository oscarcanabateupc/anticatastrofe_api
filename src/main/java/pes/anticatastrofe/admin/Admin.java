package pes.anticatastrofe.admin;

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
public class Admin {
    @Id
    private String email;
    private String regionality;

    @OneToOne
    private Person person;

    public Admin (AdminDTOIn a, Person p){
        email = a.getEmail();
        regionality = a.getRegionality();
        person = p;
    }
}
