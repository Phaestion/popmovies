package com.example.developer.popmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MoviePoster implements Serializable {
    private final int id, voteCount;
    private final boolean adult, video;
    private final double voteAverage, popularity;
    private final String title, posterPath, originalLanguage, originalTitle, backdropPath, overview, releaseDate;
    private final ArrayList<Integer> genreIds = new ArrayList<>();

    public MoviePoster(JSONObject job) throws JSONException {
        id = job.getInt("id");
        voteCount = job.getInt("vote_count");

        adult = job.getBoolean("adult");
        video = job.getBoolean("video");

        voteAverage = job.getDouble("vote_average");
        popularity = job.getDouble("popularity");

        title = job.getString("title");
        posterPath = job.getString("poster_path");
        originalLanguage = job.getString("original_language");
        originalTitle = job.getString("original_title");
        backdropPath = job.getString("backdrop_path");
        overview = job.getString("overview");
        releaseDate = job.getString("release_date");

        JSONArray jarr = job.getJSONArray("genre_ids");

        for (int i = 0; i < jarr.length(); i++) {
            genreIds.add(jarr.getInt(i));
        }
    }

    public int getId() {
        return id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isAdult() {
        return adult;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ArrayList<Integer> getGenreIds() {
        return genreIds;
    }
}