package pes.anticatastrofe.user;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    String email;
    float last_coordinate_x;
    float last_coordinate_y;
    float last_coordinate_z;

    public User(){

    }

    public User(String email, float last_coordinate_x, float last_coordinate_y, float last_coordinate_z) {
        this.email = email;
        this.last_coordinate_x = last_coordinate_x;
        this.last_coordinate_y = last_coordinate_y;
        this.last_coordinate_z = last_coordinate_z;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLast_coordinate_x(float last_coordinate_x) {
        this.last_coordinate_x = last_coordinate_x;
    }

    public void setLast_coordinate_y(float last_coordinate_y) {
        this.last_coordinate_y = last_coordinate_y;
    }

    public void setLast_coordinate_z(float last_coordinate_z) {
        this.last_coordinate_z = last_coordinate_z;
    }

    public String getEmail() {
        return email;
    }

    public float getLast_coordinate_x() {
        return last_coordinate_x;
    }

    public float getLast_coordinate_y() {
        return last_coordinate_y;
    }

    public float getLast_coordinate_z() {
        return last_coordinate_z;
    }


}