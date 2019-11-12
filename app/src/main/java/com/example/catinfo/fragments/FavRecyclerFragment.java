package com.example.catinfo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catinfo.CatAdapter;
import com.example.catinfo.Favourites;
import com.example.catinfo.R;
import com.example.catinfo.activities.MainActivity;
import com.example.catinfo.model.Cat;

import java.util.ArrayList;


public class FavRecyclerFragment extends Fragment {
    RecyclerView recyclerView;

    public FavRecyclerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);


        ArrayList<Cat> favCats = MainActivity.getFavourites().getFavCats();

        final CatAdapter catAdapter = new CatAdapter();

        catAdapter.setData(favCats);
        recyclerView.setAdapter(catAdapter);

        return view;
    }


}
