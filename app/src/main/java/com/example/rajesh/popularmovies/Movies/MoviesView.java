package com.example.rajesh.popularmovies.Movies;

import com.example.rajesh.popularmovies.View;
import com.example.rajesh.popularmovies.rest.model.Movie;

import java.util.ArrayList;


public interface MoviesView extends View {
    void showProgress();

    void hideProgress();

    void showMovies(ArrayList<Movie> movies);

    void onFailure(String message);

    boolean checkIfMoviesEmpty();

    void addMoreMovies(ArrayList<Movie> movies);
}
