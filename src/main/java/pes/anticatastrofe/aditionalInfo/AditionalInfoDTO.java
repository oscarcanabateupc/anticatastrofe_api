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
        street = ai.street;
        city = ai.city;
        state = ai.state;
        postal_code = ai.postal_code;
        country = ai.country;
        path_profile_pic = ai.path_profile_pic;
        email = ai.email;
    }
}
