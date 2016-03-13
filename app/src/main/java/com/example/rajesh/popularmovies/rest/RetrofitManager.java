package com.example.rajesh.popularmovies.rest;


import com.example.rajesh.popularmovies.rest.model.MovieComments;
import com.example.rajesh.popularmovies.rest.model.MovieTrailerInfo;
import com.example.rajesh.popularmovies.rest.model.MoviesInfo;
import com.example.rajesh.popularmovies.rest.service.IMovieService;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Singleton class for making the network call for specific url,getting response.
 */
public class RetrofitManager {
    public static final String MOVIE_BASE_URL = "http://api.themoviedb.org/";
    public static final String API_KEY = "3d9f6ef05faa3072ee2caf7fb6870964";
    public static final String YOUTUBE_INTENT_BASE_URI = "vnd.youtube://";
    public static final String MOVIE_OBJECT = "movie_object";
    public static Retrofit retrofit = null;
    public static IMovieService iMovieService = null;
    public static RetrofitManager retrofitManager = null;

    private RetrofitManager() {

        retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iMovieService = retrofit.create(IMovieService.class);
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }

    /**
     * get movies information
     *
     * @param categories categories {popular,top_rated}
     * @param page       for getting data of specific page number
     * @param apiKey     api key that is provided by themoviedb.com
     * @param callback   callback for getting response
     */
    public void getMoviesInfo(String categories, int page, String apiKey, Callback<MoviesInfo> callback) {
        Call<MoviesInfo> moviesInfoCall = iMovieService.getMoviesInfo(categories, page, apiKey);
        moviesInfoCall.enqueue(callback);
    }

    /**
     * gets comment of single movie having specific movieId
     *
     * @param movieId  id of movie
     * @param apiKey   api key that is provided by themoviedb.com
     * @param callback callback for getting response
     */

    public void getComments(int movieId, String apiKey, Callback<MovieComments> callback) {
        Call<MovieComments> movieCommentsCall = iMovieService.getComments(movieId, apiKey);
        movieCommentsCall.enqueue(callback);
    }

    /**
     * gets the key for movie trailer
     *
     * @param movieId  id of movie
     * @param apiKey   api key that is provided by themoviedb.com
     * @param callback callback for getting response
     */

    public void getTrailer(int movieId, String apiKey, Callback<MovieTrailerInfo> callback) {
        Call<MovieTrailerInfo> movieCommentsCall = iMovieService.getMovieTrailer(movieId, apiKey);
        movieCommentsCall.enqueue(callback);
    }
}
