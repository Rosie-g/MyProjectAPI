package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Getter @Setter
@ToString

public class Spartan {

    private String name;
    private String gender;
    private long phone;

    public Spartan(){

    }

    public Spartan(String name, String gender, long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }


    public String getName(String name) {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
