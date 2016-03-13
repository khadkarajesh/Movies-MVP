package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.rest.model.MovieTrailer;

import java.util.ArrayList;

/**
 * Created by rajesh on 3/14/16.
 */
public interface OnTrailerLoadListener {
    void onTrailerLoadSuccess(ArrayList<MovieTrailer> movieTrailers);

    void onTrailerLoadFailure(String message);
}
