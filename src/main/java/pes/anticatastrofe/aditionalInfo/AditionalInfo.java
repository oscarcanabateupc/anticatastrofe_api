package pes.anticatastrofe.aditionalInfo;

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
public class AditionalInfo {
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private String path_profile_pic;
    @Id
    private String email;

    @OneToOne
    private Person person;
}
