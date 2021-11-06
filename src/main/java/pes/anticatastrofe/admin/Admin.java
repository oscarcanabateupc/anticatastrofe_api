package pes.anticatastrofe.admin;

import lombok.Data;
import pes.anticatastrofe.person.Person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Admin {
    @Id
    String email;
    String regionality;

    @OneToOne
    Person person;
}
