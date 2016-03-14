package com.example.rajesh.popularmovies.Movies;


import com.example.rajesh.popularmovies.Presenter;

public interface MoviesPresenterContract extends Presenter {
    void getMovies(int page);

    void loadMore(int page);
}
