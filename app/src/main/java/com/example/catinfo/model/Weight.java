package com.example.catinfo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Weight {
    @NonNull
    @PrimaryKey
    @SerializedName("imperial")
    private String weightImperial;

    public void setWeightImperial(String weightImperial) {
        this.weightImperial = weightImperial;
    }

    public String getWeightImperial() {
        return weightImperial;
    }
}
