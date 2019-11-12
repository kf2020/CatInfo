package com.example.catinfo;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.catinfo.model.Weight;
import com.google.gson.Gson;

public class Converters {
    private static Gson gson = new Gson();
    @TypeConverter
    public static Weight stringToWeight(String data) {
        if (data == null) {
            return new Weight();
        }

        Weight weight = new Weight();

        return gson.fromJson(data, Weight.class);
    }

    @TypeConverter
    public static String WeightToString(Weight weight) {
        return gson.toJson(weight);
    }
}
