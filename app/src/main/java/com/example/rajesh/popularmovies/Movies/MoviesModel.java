package com.example.rajesh.popularmovies.Movies;

import com.example.rajesh.popularmovies.BuildConfig;
import com.example.rajesh.popularmovies.rest.RetrofitManager;
import com.example.rajesh.popularmovies.rest.model.Movie;
import com.example.rajesh.popularmovies.rest.model.MoviesInfo;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MoviesModel implements MoviesModelContract {

    @Override
    public void getMovies(final OnMovieLoadListener movieLoadListener, int page) {
        RetrofitManager.getInstance();
        Callback<MoviesInfo> moviesInfoCallback = new Callback<MoviesInfo>() {
            @Override
            public void onResponse(Response<MoviesInfo> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    movieLoadListener.onSuccess((ArrayList<Movie>) response.body().movieList);
                } else {
                    movieLoadListener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                movieLoadListener.onFailure(t.getLocalizedMessage());
            }
        };
        RetrofitManager.getInstance().getMoviesInfo("popular", page, BuildConfig.MOVIE_API_KEY, moviesInfoCallback);
    }
}
