package com.example.catinfo.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.catinfo.AppDatabase;
import com.example.catinfo.CatAdapter;
import com.example.catinfo.R;
import com.example.catinfo.model.Cat;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CatRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;
    private EditText searchInput;
    private Button searchButton;

    public CatRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cat_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        searchButton = view.findViewById(R.id.search_button);
        searchInput = view.findViewById(R.id.search_input);

        final AppDatabase db = AppDatabase.getInstance(this.getContext());

        final CatAdapter catAdapter = new CatAdapter();

        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());

        String url = "https://api.thecatapi.com/v1/breeds";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Cat[] allCatsResponse = gson.fromJson(response, Cat[].class);

                List<Cat> cats = Arrays.asList(allCatsResponse);

                db.catDao().insertCats(cats);

                catAdapter.setData(db.catDao().getAllCatsSorted());
                recyclerView.setAdapter(catAdapter);

                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);

        requestQueue.add(stringRequest);

        setOnClick(searchButton, catAdapter);

        return view;
    }

    private void setOnClick(final Button btn, final CatAdapter catAdapter){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());

                String url = "https://api.thecatapi.com/v1/breeds/search?q="+searchInput.getText();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        Cat[] filteredCatsResponse = gson.fromJson(response, Cat[].class);

                        List<Cat> catsFiltered = Arrays.asList(filteredCatsResponse);

                        catAdapter.setData(catsFiltered);
                        recyclerView.setAdapter(catAdapter);

                        requestQueue.stop();
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        requestQueue.stop();
                    }
                };

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                        errorListener);

                requestQueue.add(stringRequest);
            }
        });
    }

}
