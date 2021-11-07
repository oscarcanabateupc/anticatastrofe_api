package pes.anticatastrofe.user;

import lombok.Data;


@Data
public class UserDTO {
    String email;
    float last_coordinate_x;
    float last_coordinate_y;
    float last_coordinate_z;

    public UserDTO(User u) {
        email = u.email;
        last_coordinate_x = u.last_coordinate_x;
        last_coordinate_y = u.last_coordinate_y;
        last_coordinate_z = u.last_coordinate_z;
    }
}