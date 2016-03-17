package com.example.rajesh.popularmovies.Movies;

import com.example.rajesh.popularmovies.BuildConfig;
import com.example.rajesh.popularmovies.PopularMovieApplication;
import com.example.rajesh.popularmovies.rest.model.Movie;
import com.example.rajesh.popularmovies.rest.model.MoviesInfo;
import com.example.rajesh.popularmovies.rest.service.IMovieService;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MoviesModel implements MoviesModelContract {

    @Inject
    IMovieService iMovieService;

    public MoviesModel() {
        PopularMovieApplication.getMovieComponent().inject(this);
    }

    @Override
    public void getMovies(final OnMovieLoadListener movieLoadListener, int page) {
        Call<MoviesInfo> moviesInfoCall = iMovieService.getMoviesInfo("popular", page, BuildConfig.MOVIE_API_KEY);
        moviesInfoCall.enqueue(new Callback<MoviesInfo>() {
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
        });

    }
}
