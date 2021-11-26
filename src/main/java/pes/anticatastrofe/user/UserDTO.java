package pes.anticatastrofe.user;

import lombok.Data;


@Data
public class UserDTO {
    private String email;
    private float last_coordinate_x;
    private float last_coordinate_y;
    private String name;
    private int phone_num;

    public UserDTO(User u) {
        email = u.getEmail();
        last_coordinate_x = u.getLast_coordinate_x();
        last_coordinate_y = u.getLast_coordinate_y();
        name = u.getPerson().getEmail();
        phone_num = u.getPerson().getPhone_num();
    }
}