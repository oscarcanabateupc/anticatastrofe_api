package pes.anticatastrofe.aditionalInfo;

import lombok.Data;
import pes.anticatastrofe.person.Person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@Data
public class AditionalInfo {
    String street;
    String city;
    String state;
    String postal_code;
    String country;
    String path_profile_pic;
    @Id
    String email;

    @OneToOne
    Person person;
}
