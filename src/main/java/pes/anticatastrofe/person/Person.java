package pes.anticatastrofe.person;

import pes.anticatastrofe.user.User;

import javax.persistence.*;

@Entity
@Table
public class Person {
    String name;
    int phone_num;
    @Id String email;
    String password;

    public Person() {
    }

    public Person(String name, int phone_num, String email, String password) {
        this.name = name;
        this.phone_num = phone_num;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
