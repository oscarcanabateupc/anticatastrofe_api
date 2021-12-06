package pes.anticatastrofe.admin;

import lombok.Data;

@Data
public class AdminDTOIn {
    private String email;
    private String regionality;
    private String name;
    private int phone_num;
    private String password;
    private String token;
}
