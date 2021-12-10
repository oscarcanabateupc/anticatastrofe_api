package pes.anticatastrofe.aditionalInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pes.anticatastrofe.person.Person;

import javax.persistence.*;
import java.util.Date;

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
    private String blood_type;
    private Date birth_date;
    @Id
    private String email;

    @OneToOne
    private Person person;

    public AditionalInfo(AditionalInfoDTOIn a,Person p){
        street = a.getStreet();
        city = a.getCity();
        state = a.getState();
        postal_code = a.getPostal_code();
        country = a.getCountry();
        path_profile_pic = a.getPath_profile_pic();
        blood_type = a.getBlood_type();
        birth_date = a.getBirth_date();
        email = a.getEmail();
        person = p;
    }
}
