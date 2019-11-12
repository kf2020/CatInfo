package com.example.catinfo;

import com.example.catinfo.model.Cat;

import java.util.ArrayList;
import java.util.HashMap;

public class Favourites {
    private static HashMap<String, Cat> favCats;

    public Favourites() {
        favCats = new HashMap<>();
    }

    public ArrayList<Cat> getFavCats() {
        return new ArrayList(favCats.values());
    }

    public boolean isCatFav(Cat cat) {
        if (favCats.get(cat.getId()) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void addFavCat(Cat cat) {
        favCats.put(cat.getId(), cat);
    }

    public void removeFavCat(Cat cat) {
        favCats.remove(cat.getId());
    }
}
