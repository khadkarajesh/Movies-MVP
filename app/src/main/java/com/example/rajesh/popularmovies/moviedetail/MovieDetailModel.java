package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.BuildConfig;
import com.example.rajesh.popularmovies.rest.RetrofitManager;
import com.example.rajesh.popularmovies.rest.model.Movie;
import com.example.rajesh.popularmovies.rest.model.MovieComment;
import com.example.rajesh.popularmovies.rest.model.MovieComments;
import com.example.rajesh.popularmovies.rest.model.MovieTrailer;
import com.example.rajesh.popularmovies.rest.model.MovieTrailerInfo;

import java.util.ArrayList;

import retrofit.Response;
import retrofit.Retrofit;

/**
 *
 */
public class MovieDetailModel implements MovieDetailModelContract {

    @Override
    public void getComments(int movieId, final OnCommentLoadListener onCommentLoadListener) {
        retrofit.Callback<MovieComments> callback = new retrofit.Callback<MovieComments>() {
            @Override
            public void onResponse(Response<MovieComments> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    onCommentLoadListener.onCommentLoadSuccess((ArrayList<MovieComment>) response.body().movieCommentList);
                } else {
                    onCommentLoadListener.onCommentLoadFailure(response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                onCommentLoadListener.onCommentLoadFailure(t.getLocalizedMessage());
            }
        };
        RetrofitManager.getInstance().getComments(movieId, BuildConfig.MOVIE_API_KEY, callback);
    }

    @Override
    public void getTrailers(int movieId, final OnTrailerLoadListener onTrailerLoadListener) {

        retrofit.Callback<MovieTrailerInfo> movieTrailerInfoCallback = new retrofit.Callback<MovieTrailerInfo>() {
            @Override
            public void onResponse(Response<MovieTrailerInfo> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body().movieTrailers.size() > 0) {
                    onTrailerLoadListener.onTrailerLoadSuccess((ArrayList<MovieTrailer>) response.body().movieTrailers);
                } else {
                    onTrailerLoadListener.onTrailerLoadFailure(response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                onTrailerLoadListener.onTrailerLoadFailure(t.getLocalizedMessage());
            }
        };
        RetrofitManager.getInstance().getTrailer(movieId, BuildConfig.MOVIE_API_KEY, movieTrailerInfoCallback);
    }

    @Override
    public void saveFavouriteMovie(Movie movie) {

    }
}
