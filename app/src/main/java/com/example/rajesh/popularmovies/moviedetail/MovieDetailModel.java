package com.example.rajesh.popularmovies.moviedetail;

import com.example.rajesh.popularmovies.BuildConfig;
import com.example.rajesh.popularmovies.PopularMovieApplication;
import com.example.rajesh.popularmovies.rest.model.Movie;
import com.example.rajesh.popularmovies.rest.model.MovieComment;
import com.example.rajesh.popularmovies.rest.model.MovieComments;
import com.example.rajesh.popularmovies.rest.model.MovieTrailer;
import com.example.rajesh.popularmovies.rest.model.MovieTrailerInfo;
import com.example.rajesh.popularmovies.rest.service.IMovieService;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 *
 */
public class MovieDetailModel implements MovieDetailModelContract {

    @Inject
    IMovieService iMovieService;

    public MovieDetailModel() {
        PopularMovieApplication.getMovieComponent().inject(this);
    }

    @Override
    public void getComments(int movieId, final OnCommentLoadListener onCommentLoadListener) {
        Call<MovieComments> movieCommentsCall = iMovieService.getComments(movieId, BuildConfig.MOVIE_API_KEY);
        movieCommentsCall.enqueue(new Callback<MovieComments>() {
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
        });
    }

    @Override
    public void getTrailers(int movieId, final OnTrailerLoadListener onTrailerLoadListener) {
        Call<MovieTrailerInfo> movieTrailerInfoCall = iMovieService.getMovieTrailer(movieId, BuildConfig.MOVIE_API_KEY);
        movieTrailerInfoCall.enqueue(new Callback<MovieTrailerInfo>() {
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
        });
    }

    @Override
    public void saveFavouriteMovie(Movie movie) {
        //// TODO: 3/17/16 later save to database
    }
}
