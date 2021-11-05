package pes.anticatastrofe.admin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class AdminDTO {
    String email;
    String regionality;

    public AdminDTO(Admin a) {
        email = a.email;
        regionality = a.regionality;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegionality() {
        return regionality;
    }

    public void setRegionality(String regionality) {
        this.regionality = regionality;
    }
}
