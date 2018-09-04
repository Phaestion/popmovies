package com.example.developer.popmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar loadingProgress;
    RecyclerView moviePostersRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingProgress= findViewById(R.id.pb_loading);
        moviePostersRecycler= findViewById(R.id.rc_movie_posters);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private class GetPopularMovies extends AsyncTask<String, Void, List<MoviePoster>>{


        @Override
        protected List<MoviePoster> doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(List<MoviePoster> moviePosters) {
            super.onPostExecute(moviePosters);
        }
    }
}
