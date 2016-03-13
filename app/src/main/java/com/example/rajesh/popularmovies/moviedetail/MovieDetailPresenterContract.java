package com.example.rajesh.popularmovies.moviedetail;

/**
 *
 */
public interface MovieDetailPresenterContract extends Presenter {
    void getMovieTrailers(int movieId);

    void getMovieComments(int movieId);
}
