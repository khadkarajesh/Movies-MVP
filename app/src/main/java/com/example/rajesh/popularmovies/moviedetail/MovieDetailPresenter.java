package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.rest.model.Movie;
import com.example.rajesh.popularmovies.rest.model.MovieComment;
import com.example.rajesh.popularmovies.rest.model.MovieTrailer;

import java.util.ArrayList;

/**
 * Created by rajesh on 3/14/16.
 */
public class MovieDetailPresenter implements MovieDetailPresenterContract, OnCommentLoadListener, OnTrailerLoadListener, OnMovieSaveListener {

    MovieDetailView movieDetailView;
    MovieDetailModelContract movieDetailModelContract;

    public MovieDetailPresenter(MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
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
    public void addToFavorite(Movie movie) {
        movieDetailModelContract.saveFavouriteMovie(movie);
    }

    @Override
    public void onCommentLoadSuccess(ArrayList<MovieComment> movieComments) {
        if (movieComments.size() > 0) {
            movieDetailView.showCommentTextView();
            movieDetailView.showComments(movieComments);
        } else {
            movieDetailView.hideCommentTextView();
        }
    }

    @Override
    public void onCommentLoadFailure(String message) {
        movieDetailView.onFailure(message);
    }

    @Override
    public void onTrailerLoadSuccess(ArrayList<MovieTrailer> movieTrailers) {
        if (movieTrailers.size() > 0) {
            movieDetailView.showTrailerTextView();
            movieDetailView.showMovieTrailers(movieTrailers);
        } else {
            movieDetailView.hideTrailerTextView();
        }
    }

    @Override
    public void onTrailerLoadFailure(String message) {
        movieDetailView.onFailure(message);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onMovieSavedSuccess() {

    }

    @Override
    public void onMovieSaveFailure() {

    }
}
