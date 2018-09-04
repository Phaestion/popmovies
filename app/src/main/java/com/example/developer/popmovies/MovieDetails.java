package com.example.developer.popmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developer.popmovies.Helpers.Consts;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    private TextView titleTV;
    private ImageView posterIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        titleTV = findViewById(R.id.tv_detail_title);
        posterIV = findViewById(R.id.iv_detail_poster);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(Consts.EXTRA_MOVIE_DETAILS)) {
            MoviePoster movie = (MoviePoster) intent.getSerializableExtra(Consts.EXTRA_MOVIE_DETAILS);

            titleTV.setText(movie.getTitle());

            Picasso.with(this)
                    .load(Consts.moviePosterUrl + movie.getPosterPath())
                    .into(posterIV);

        }
    }
}
