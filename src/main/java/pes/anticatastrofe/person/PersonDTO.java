package pes.anticatastrofe.person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class PersonDTO {
    String name;
    int phone_num;
    String email;

    public PersonDTO(Person p) {
        name = p.name;
        phone_num = p.phone_num;
        email = p.email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(int phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
