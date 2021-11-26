package pes.anticatastrofe.person;

import lombok.Data;

@Data
public class PersonDTO {
    private String name;
    private int phone_num;
    private String email;

    public PersonDTO(Person p) {
        name = p.getName();
        phone_num = p.getPhone_num();
        email = p.getEmail();
    }

}
