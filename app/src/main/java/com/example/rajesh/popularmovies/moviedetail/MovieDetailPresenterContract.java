package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.Presenter;

/**
 *
 */
public interface MovieDetailPresenterContract extends Presenter {
    void getMovieTrailers(int movieId);

    void getMovieComments(int movieId);
}
