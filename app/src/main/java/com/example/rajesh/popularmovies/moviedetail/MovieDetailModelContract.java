package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.rest.model.Movie;

/**
 *
 */
public interface MovieDetailModelContract {
    void getComments(int movieId, OnCommentLoadListener onCommentLoadListener);

    void getTrailers(int movieId, OnTrailerLoadListener onTrailerLoadListener);

    void saveFavouriteMovie(Movie movie);
}
