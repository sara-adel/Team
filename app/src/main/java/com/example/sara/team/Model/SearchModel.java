package com.example.sara.team.Model;

import java.io.Serializable;

/**
 * Created by Sara on 5/8/2017.
 */
public class SearchModel implements Serializable{
    String image;
    String name ;
    String country;
    String email;

    public SearchModel(String image, String name, String country, String email) {
        this.image = image;
        this.name = name;
        this.country = country;
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
