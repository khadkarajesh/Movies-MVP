package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.Presenter;
import com.example.rajesh.popularmovies.rest.model.Movie;

/**
 *
 */
public interface MovieDetailPresenterContract extends Presenter {
    void getMovieTrailers(int movieId);

    void getMovieComments(int movieId);

    void addToFavorite(Movie movie);
}
