package com.example.catinfo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


public class Weight {
    @SerializedName("imperial")
    private String weightImperial;

    public Weight() {
    }

    public Weight(String weight) {
        this.weightImperial = weight;
    }

    public void setWeightImperial(String weightImperial) {
        this.weightImperial = weightImperial;
    }

    public String getWeightImperial() {
        return weightImperial;
    }
}
