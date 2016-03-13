package com.example.rajesh.popularmovies.Movies;


public interface MoviesModelContract {
    void getMovies(OnMovieLoadListener movieLoadListener,int page);
}
