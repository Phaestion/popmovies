package com.example.developer.popmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.developer.popmovies.Helpers.Consts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements MoviePosterAdapter.ItemClickListener {
    private static final String TAG = "MainActivity";

    private ProgressBar loadingProgress;
    private RecyclerView moviePostersRecycler;
    private List<MoviePoster> movies;

    private String url = Consts.popMoviesUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingProgress = findViewById(R.id.pb_loading);
        moviePostersRecycler = findViewById(R.id.rc_movie_posters);

        moviePostersRecycler.setHasFixedSize(true);
        moviePostersRecycler.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (movies == null) {
            loadMovies();
        } else {
            showMovies(movies);
        }
    }


    private void loadMovies() {
        loadingProgress.setVisibility(View.VISIBLE);
        moviePostersRecycler.setVisibility(View.GONE);

        new GetPopularMovies().execute(url);
    }


    private void showMovies(List<MoviePoster> movies) {
        loadingProgress.setVisibility(View.GONE);
        moviePostersRecycler.setVisibility(View.VISIBLE);

        this.movies = movies;
        moviePostersRecycler.setAdapter(new MoviePosterAdapter(movies, this));
    }

    @Override
    public void onItemClicked(int position) {
        Log.d(TAG, "Got click on " + position);

        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Consts.EXTRA_MOVIE_DETAILS, movies.get(position));

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_sort_pop:
                url = Consts.popMoviesUrl;
                loadMovies();
                return true;

            case R.id.action_sort_rating:
                url = Consts.topMoviesUrl;
                loadMovies();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetPopularMovies extends AsyncTask<String, Void, List<MoviePoster>> {
        private Exception error = null;

        @Override
        protected List<MoviePoster> doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();

            try {
                Response response = client.newCall(request).execute();

                if (response.code() == 200) {
                    JSONObject job = new JSONObject(response.body().string());

                    JSONArray jarr = job.getJSONArray("results");
                    ArrayList<MoviePoster> movies = new ArrayList<>();

                    for (int i = 0; i < jarr.length(); i++) {
                        movies.add(new MoviePoster(jarr.getJSONObject(i)));
                    }

                    return movies;
                }

            } catch (IOException | JSONException e) {
                error = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<MoviePoster> moviePosters) {
            super.onPostExecute(moviePosters);

            if (moviePosters != null && error == null) {
                //Successfully got movies

                Log.d(TAG, "Got " + moviePosters.size() + " from themoviedb.org");

                showMovies(moviePosters);
            } else if (error != null) {
                Log.e(TAG, "Error fetching movies", error);
            } else {
                Log.e(TAG, "Unknown error fetching movies");
            }
        }
    }
}
