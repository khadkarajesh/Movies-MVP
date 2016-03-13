package com.example.rajesh.popularmovies.Movies;

import com.example.rajesh.popularmovies.rest.model.Movie;

import java.util.ArrayList;

public interface OnMovieLoadListener {
    void onSuccess(ArrayList<Movie> movies);

    void onFailure(String message);
}
