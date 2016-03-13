package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.rest.model.MovieComment;
import com.example.rajesh.popularmovies.rest.model.MovieTrailer;

import java.util.ArrayList;

/**
 * Created by rajesh on 3/14/16.
 */
public class MovieDetailPresenter implements MovieDetailPresenterContract, OnCommentLoadListener, OnTrailerLoadListener {

    MovieDetail movieDetail;
    MovieDetailModelContract movieDetailModelContract;

    public MovieDetailPresenter(MovieDetail movieDetail) {
        this.movieDetail = movieDetail;
        movieDetailModelContract = new MovieDetailModel();
    }

    @Override
    public void getMovieTrailers(int movieId) {
        movieDetailModelContract.getTrailers(movieId, this);
    }

    @Override
    public void getMovieComments(int movieId) {
        movieDetailModelContract.getComments(movieId, this);
    }

    @Override
    public void onCommentLoadSuccess(ArrayList<MovieComment> movieComments) {
        if (movieComments.size() > 0) {
            movieDetail.showCommentTextView();
            movieDetail.showComments(movieComments);
        } else {
            movieDetail.hideCommentTextView();
        }
    }

    @Override
    public void onCommentLoadFailure(String message) {
        movieDetail.onFailure(message);
    }

    @Override
    public void onTrailerLoadSuccess(ArrayList<MovieTrailer> movieTrailers) {
        if (movieTrailers.size() > 0) {
            movieDetail.showTrailerTextView();
            movieDetail.showMovieTrailers(movieTrailers);
        } else {
            movieDetail.hideTrailerTextView();
        }
    }

    @Override
    public void onTrailerLoadFailure(String message) {
        movieDetail.onFailure(message);
    }
}
