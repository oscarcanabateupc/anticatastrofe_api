package pes.anticatastrofe.aditionalInfo;

import lombok.Data;

import java.util.Date;

@Data
public class AditionalInfoDTO {
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private String path_profile_pic;
    private String blood_type;
    private Date birth_date;
    private String email;

    public AditionalInfoDTO(AditionalInfo ai) {
        street = ai.getStreet();
        city = ai.getCity();
        state = ai.getState();
        postal_code = ai.getPostal_code();
        country = ai.getCountry();
        path_profile_pic = ai.getPath_profile_pic();
        blood_type = ai.getBlood_type();
        birth_date = ai.getBirth_date();
        email = ai.getEmail();
    }
}
