package pes.anticatastrofe.admin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Admin {
    @Id
    String email;
    String regionality;

    public Admin(String email, String regionality) {
        this.email = email;
        this.regionality = regionality;
    }

    public Admin(String email) {
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
