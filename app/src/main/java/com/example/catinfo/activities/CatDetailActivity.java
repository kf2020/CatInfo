package com.example.catinfo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.catinfo.AppDatabase;
import com.example.catinfo.Favourites;
import com.example.catinfo.R;
import com.example.catinfo.model.Cat;

/*@NonNull
    @PrimaryKey
    String id;

    String name;
    String description;

    String referenceImgId;

    // Weight weight;

    String temperament;
    String origin;

    String lifeSpan;

    @SerializedName("wikipedia_url")
    String wikipediaUrl;
    int dogFriendly;*/

public class CatDetailActivity extends AppCompatActivity {
    ConstraintLayout catConstraintLayout;
    TextView nameTextView;
    TextView descriptionTextView;
    TextView origin;
    TextView weight;
    TextView temperament;
    TextView lifespan;
    TextView dogFriendly;
    ConstraintLayout favourite;
    ImageView favouriteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        catConstraintLayout = findViewById(R.id.cat);
        nameTextView = catConstraintLayout.findViewById(R.id.name);
        descriptionTextView = catConstraintLayout.findViewById(R.id.description);
        favourite = catConstraintLayout.findViewById(R.id.favourite_layout);
        favouriteBtn = catConstraintLayout.findViewById(R.id.favorite_btn);
        origin = catConstraintLayout.findViewById(R.id.origin);
        weight = catConstraintLayout.findViewById(R.id.weight);
        temperament = catConstraintLayout.findViewById(R.id.temperament);
        lifespan = catConstraintLayout.findViewById(R.id.lifespan);
        dogFriendly = catConstraintLayout.findViewById(R.id.dog_friendly);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        final Cat cat = db.catDao().findCatById(id);

        nameTextView.setText(cat.getName());
        descriptionTextView.setText(cat.getDescription());
        origin.setText(cat.getOrigin());
        weight.setText(cat.getWeight());
        temperament.setText(cat.getTemperament());
        lifespan.setText(cat.getLifeSpan());
        int dogFriendlyInt = cat.getDogFriendly();
        String dogFriendlyStr;
        if (dogFriendlyInt > 0) {
            dogFriendlyStr = Integer.toString(dogFriendlyInt);
        } else {
            dogFriendlyStr = "N/A";
        }
        dogFriendly.setText(dogFriendlyStr);

        if (MainActivity.getFavourites().isCatFav(cat)){
            favouriteBtn.setImageResource(android.R.drawable.star_on);
        } else {
            favouriteBtn.setImageResource(android.R.drawable.star_off);
        }

        favourite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (!MainActivity.getFavourites().isCatFav(cat)){
                    MainActivity.getFavourites().addFavCat(cat);
                    favouriteBtn.setImageResource(android.R.drawable.star_on);
                } else {
                    MainActivity.getFavourites().removeFavCat(cat);
                    favouriteBtn.setImageResource(android.R.drawable.star_off);
                }
            }

        });

        //String imageUrl = cat.getReferenceImgId();
       // Glide.with(this).load(imageUrl).into(coverImageView);
    }
}
