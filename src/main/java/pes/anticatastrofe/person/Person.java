package pes.anticatastrofe.person;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Person {
    String name;
    int phone_num;
    @Id
    String email;
    String password;
}
