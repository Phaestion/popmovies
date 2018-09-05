package com.example.developer.popmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developer.popmovies.Helpers.Consts;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTv, overviewTv, releaseYearTv, ratingTv;
    private ImageView posterIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        titleTv = findViewById(R.id.tv_detail_title);
        posterIv = findViewById(R.id.iv_detail_poster);
        overviewTv = findViewById(R.id.tv_detail_overview);
        releaseYearTv = findViewById(R.id.tv_detail_release_year);
        ratingTv = findViewById(R.id.tv_detail_rating);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(Consts.EXTRA_MOVIE_DETAILS)) {
            MoviePoster movie = (MoviePoster) intent.getSerializableExtra(Consts.EXTRA_MOVIE_DETAILS);

            titleTv.setText(movie.getTitle());
            overviewTv.setText(movie.getOverview());
            releaseYearTv.setText(String.format(getString(R.string.reslease_Date), movie.getReleaseDate()));
            ratingTv.setText(String.format(getString(R.string.rating), String.valueOf(movie.getVoteAverage())));

            Picasso.with(this)
                    .load(Consts.moviePosterUrl + movie.getPosterPath())
                    .into(posterIv);

        }
    }
}
