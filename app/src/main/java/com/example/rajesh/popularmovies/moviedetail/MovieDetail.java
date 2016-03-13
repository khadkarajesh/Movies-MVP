package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.rest.model.MovieComment;
import com.example.rajesh.popularmovies.rest.model.MovieTrailer;

import java.util.ArrayList;

/**
 *
 */
public interface MovieDetail {
    void showComments(ArrayList<MovieComment> movieComments);

    void showMovieTrailers(ArrayList<MovieTrailer> movieTrailers);

    void showCommentTextView();

    void hideCommentTextView();

    void showTrailerTextView();

    void hideTrailerTextView();

    void onFailure(String message);
}
