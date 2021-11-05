package pes.anticatastrofe.user;

import javax.persistence.*;

public class UserDTO {
    String email;
    float last_coordinate_x;
    float last_coordinate_y;
    float last_coordinate_z;

    public UserDTO(User u){
        email = u.email;
        last_coordinate_x = u.last_coordinate_x;
        last_coordinate_y = u.last_coordinate_y;
        last_coordinate_z = u.last_coordinate_z;
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