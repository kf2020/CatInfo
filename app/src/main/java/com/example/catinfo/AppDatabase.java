package com.example.catinfo;

import androidx.room.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.catinfo.model.Cat;
import com.example.catinfo.model.CatDao;


@Database(entities = {Cat.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CatDao catDao();

    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "catDb")
                    .allowMainThreadQueries()   // <== IMPORTANT TO NOTE:
                    //     This is NOT correct to do in a completed app.
                    //     Next week we will fix it, but for now this
                    //     line is necessary for the app to work.
                    //     This line will basically allow the database
                    //     queries to freeze the app.
                    .build();
        }
        return instance;
    }
}
