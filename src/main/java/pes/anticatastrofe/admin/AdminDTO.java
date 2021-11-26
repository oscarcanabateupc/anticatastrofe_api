package pes.anticatastrofe.admin;

import lombok.Data;
import pes.anticatastrofe.person.Person;

@Data
public class AdminDTO {
    private String email;
    private String regionality;
    private String name;
    private int phone_num;

    public AdminDTO(Admin a) {
        email = a.getEmail();
        regionality = a.getRegionality();
        if (a.getPerson() != null) {
            name = a.getPerson().getEmail();
            phone_num = a.getPerson().getPhone_num();
        }
    }
}
