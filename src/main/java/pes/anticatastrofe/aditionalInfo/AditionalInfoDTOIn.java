package pes.anticatastrofe.aditionalInfo;

import lombok.Data;

import java.util.Date;

@Data
public class AditionalInfoDTOIn {
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private String path_profile_pic;
    private String blood_type;
    private String email;
}
