package com.example.rajesh.popularmovies.moviedetail;

/**
 *
 */
public interface MovieDetailModelContract {
    void getComments(int movieId, OnCommentLoadListener onCommentLoadListener);

    void getTrailers(int movieId, OnTrailerLoadListener onTrailerLoadListener);
}
