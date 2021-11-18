package pes.anticatastrofe.aditionalInfo;

import lombok.Data;

@Data
public class AditionalInfoDTO {
    String street;
    String city;
    String state;
    String postal_code;
    String country;
    String path_profile_pic;
    String email;

    public AditionalInfoDTO(AditionalInfo ai) {
        street = ai.getStreet();
        city = ai.getCity();
        state = ai.getState();
        postal_code = ai.getPostal_code();
        country = ai.getCountry();
        path_profile_pic = ai.getPath_profile_pic();
        email = ai.getEmail();
    }
}
