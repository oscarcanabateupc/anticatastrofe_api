package pes.anticatastrofe.aditionalInfo;

import lombok.Data;

@Data
public class AditionalInfoDTOIn {
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private String path_profile_pic;
    private String email;
}
