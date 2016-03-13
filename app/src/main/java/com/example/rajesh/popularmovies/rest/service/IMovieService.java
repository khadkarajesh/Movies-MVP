package com.example.rajesh.popularmovies.rest.service;


import com.example.rajesh.popularmovies.rest.model.MovieComments;
import com.example.rajesh.popularmovies.rest.model.MovieTrailerInfo;
import com.example.rajesh.popularmovies.rest.model.MoviesInfo;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;



public interface IMovieService {
    @GET("3/movie/{categories}")
    Call<MoviesInfo> getMoviesInfo(@Path("categories") String categories, @Query("page") int page, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/reviews")
    Call<MovieComments> getComments(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/videos")
    Call<MovieTrailerInfo> getMovieTrailer(@Path("id") int id, @Query("api_key") String apiKey);
}
