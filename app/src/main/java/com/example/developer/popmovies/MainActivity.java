package com.example.developer.popmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ProgressBar loadingProgress;
    private RecyclerView moviePostersRecycler;

    private String url= Consts.popMoviesUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingProgress = findViewById(R.id.pb_loading);
        moviePostersRecycler = findViewById(R.id.rc_movie_posters);

        moviePostersRecycler.setHasFixedSize(true);
        moviePostersRecycler.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadingProgress.setVisibility(View.VISIBLE);
        moviePostersRecycler.setVisibility(View.GONE);

        new GetPopularMovies().execute(url);
    }


    public void showMovies(List<MoviePoster> movies) {

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
                e.printStackTrace();
                error = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<MoviePoster> moviePosters) {
            super.onPostExecute(moviePosters);

            if (moviePosters != null && error == null) {
                //Successfully got movies

                Log.d(TAG, "Got " + moviePosters.size() + " from themoviedb");
            }
        }
    }
}
