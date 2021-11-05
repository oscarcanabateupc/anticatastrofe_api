package pes.anticatastrofe.aditionalInfo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AditionalInfoDTO {
    String street;
    String city;
    String state;
    String postal_code;
    String country;
    String path_profile_pic;
    @Id String email;

    public AditionalInfoDTO(AditionalInfo ai) {
        street = ai.street;
        city = ai.city;
        state = ai.state;
        postal_code = ai.postal_code;
        country = ai.country;
        path_profile_pic = ai.path_profile_pic;
        email = ai.email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPath_profile_pic() {
        return path_profile_pic;
    }

    public void setPath_profile_pic(String path_profile_pic) {
        this.path_profile_pic = path_profile_pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}