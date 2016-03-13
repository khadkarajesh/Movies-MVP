package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.rest.model.MovieComment;

import java.util.ArrayList;

/**
 * Created by rajesh on 3/14/16.
 */
public interface OnCommentLoadListener {
    void onCommentLoadSuccess(ArrayList<MovieComment> movieComments);

    void onCommentLoadFailure(String message);
}
