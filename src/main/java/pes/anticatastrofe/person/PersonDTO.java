package pes.anticatastrofe.person;

import lombok.Data;

@Data
public class PersonDTO {
    String name;
    int phone_num;
    String email;

    public PersonDTO(Person p) {
        name = p.name;
        phone_num = p.phone_num;
        email = p.email;
    }

}
