package com.example.catinfo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

/*
Include:
o Name of cat breed.
o Image of the cat breed.
o Description
o Weight
o Temperament
o Origin
o Life span
o Wikipedia link
o Dog friendliness level
 */

@Entity
public class Cat {

    @NonNull
    @PrimaryKey
    String id;

    String name;
    String description;



    @SerializedName("wikipedia_url")
    String wikipediaUrl;

    Weight weight;

    String temperament;
    String origin;

    @SerializedName("life_span")
    String lifeSpan;

    @SerializedName("dog_friendly")
    int dogFriendly;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperament() {
        if (temperament != null) {
            return temperament;
        }
        return "N/A";
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        if (origin != null) {
            return origin;
        }
        return "N/A";
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLifeSpan() {
        if (lifeSpan != null) {
            return lifeSpan;
        }
        return "N/A";
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public int getDogFriendly() {
        return dogFriendly;
    }

    public void setDogFriendly(int dogFriendly) {
        this.dogFriendly = dogFriendly;
    }

    public String getWeight() {
        return weight.getWeightImperial();
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }
}

