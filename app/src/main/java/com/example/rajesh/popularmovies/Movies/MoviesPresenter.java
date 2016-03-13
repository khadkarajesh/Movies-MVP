package com.example.rajesh.popularmovies.Movies;

import com.example.rajesh.popularmovies.rest.model.Movie;

import java.util.ArrayList;


public class MoviesPresenter implements MoviesPresenterContract, OnMovieLoadListener {
    MoviesView moviesView;
    MoviesModelContract moviesModel;

    public MoviesPresenter(MoviesView moviesView) {
        this.moviesView = moviesView;
        moviesModel = new MoviesModel();
    }

    @Override
    public void getMovies(int page) {
        moviesView.showProgress();
        moviesModel.getMovies(this, page);
    }

    @Override
    public void loadMore(int page) {
        moviesModel.getMovies(this, page);
    }

    @Override
    public void onDestroy() {
        if (moviesView != null) {
            moviesView = null;
        }
        if (moviesModel != null) {
            moviesModel = null;
        }
    }

    @Override
    public void onSuccess(ArrayList<Movie> movies) {
        if (moviesView.checkIfMoviesEmpty()) {
            moviesView.showMovies(movies);
        } else {
            moviesView.addMoreMovies(movies);
        }
        moviesView.hideProgress();
    }

    @Override
    public void onFailure(String message) {
        moviesView.onFailure(message);
        moviesView.hideProgress();
    }
}
