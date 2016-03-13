package com.example.rajesh.popularmovies.Movies;


public interface MoviesPresenterContract {
    void getMovies(int page);

    void loadMore(int page);

    void onDestroy();
}
