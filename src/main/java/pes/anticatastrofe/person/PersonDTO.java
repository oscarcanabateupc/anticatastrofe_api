package pes.anticatastrofe.person;

import lombok.Data;

@Data
public class PersonDTO {
    String name;
    int phone_num;
    String email;

    public PersonDTO(Person p) {
        name = p.getName();
        phone_num = p.getPhone_num();
        email = p.getEmail();
    }

}
