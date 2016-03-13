package com.example.rajesh.popularmovies.moviedetail;

/**
 *
 */
public interface MovieDetailPresenterContract {
    void getMovieTrailers(int movieId);

    void getMovieComments(int movieId);
}
