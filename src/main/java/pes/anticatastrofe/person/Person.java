package pes.anticatastrofe.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pes.anticatastrofe.admin.AdminDTOIn;
import pes.anticatastrofe.landmark.Landmark;
import pes.anticatastrofe.user.UserDTOIn;

import javax.persistence.*;
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

    public Person(UserDTOIn u){
        email = u.getEmail();
        name = u.getName();
        phone_num = u.getPhone_num();
        password = u.getPassword();
        token = u.getToken();
    }

    public Person(AdminDTOIn a){
        email = a.getEmail();
        name = a.getName();
        phone_num = a.getPhone_num();
        password = a.getPassword();
        token = a.getToken();
    }
}
