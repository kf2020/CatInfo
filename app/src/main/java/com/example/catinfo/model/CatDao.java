package com.example.catinfo.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface CatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCat(Cat... cat);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCats(List<Cat> cats);

    @Query("SELECT * FROM cat")
    public List<Cat> getAllCats();

    /*
    @Query("SELECT * FROM cat")
    public List<Cat> getAllCatsSorted();

    @Query("SELECT * FROM book WHERE isbn = :isbnVar")
    public Cat findBookByIsbn(long isbnVar);
    */

    @Delete
    void delete(Cat cat);

}
