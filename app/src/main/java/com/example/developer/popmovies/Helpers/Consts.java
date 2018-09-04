package com.example.developer.popmovies.Helpers;

public class Consts {

    //TODO: add API key here
    private static final String apiKey = "";
    private static final String baseUrl = "https://api.themoviedb.org/3/movie/";

    public static final String popMoviesUrl = baseUrl + "popular?api_key=" + apiKey;
    public static final String topMoviesUrl = baseUrl + "top_rated?api_key=" + apiKey;

    public static final String moviePosterUrl = "https://image.tmdb.org/t/p/w185/";
    public static final String EXTRA_MOVIE_DETAILS = "extra_movie_details";
}