package pes.anticatastrofe.admin;

import lombok.Data;

@Data
public class AdminDTO {
    String email;
    String regionality;

    public AdminDTO(Admin a) {
        email = a.getEmail();
        regionality = a.getRegionality();
    }
}
