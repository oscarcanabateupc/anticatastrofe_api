package pes.anticatastrofe.user;

import lombok.Data;


@Data
public class UserDTOIn {
    private String email;
    private float last_coordinate_x;
    private float last_coordinate_y;
    private String name;
    private int phone_num;
    private String password;
    private String token;
}