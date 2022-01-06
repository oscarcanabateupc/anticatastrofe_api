package pes.anticatastrofe.aditionalInfo;

import lombok.Data;
import lombok.SneakyThrows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private String birth_date;
    private String email;

    @SneakyThrows
    public AditionalInfoDTO(AditionalInfo ai) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        street = ai.getStreet();
        city = ai.getCity();
        state = ai.getState();
        postal_code = ai.getPostal_code();
        country = ai.getCountry();
        path_profile_pic = ai.getPath_profile_pic();
        blood_type = ai.getBlood_type();
        birth_date = formatter.format(ai.getBirth_date());
        email = ai.getEmail();
    }
}
