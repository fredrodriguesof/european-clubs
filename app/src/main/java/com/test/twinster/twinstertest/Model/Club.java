package com.test.twinster.twinstertest.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties({"images"})
public class Club implements Comparable<Club>, Serializable {

    private String name;
    private String country;
    private int value;
    private String image;

    @Override
    public int compareTo(Club club) {
        return this.getName().compareTo(club.getName());
    }

    public Club(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

