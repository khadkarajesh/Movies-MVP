package com.example.rajesh.popularmovies.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.example.rajesh.popularmovies.R;


/**
 */
public class Utility {

    private static final String IMAGE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private static final String IMAGE_POSTER_SMALL_BASE_URL = "http://image.tmdb.org/t/p/w185";

    public static String getImageUri(String uri) {
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }

    public static String getImageUriOfSmallSize(String uri) {
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }

    /**
     * get movie categories that is stored in the default sharedPreferences
     *
     * @return returns the movie categories{i-e popular/top_rated}
     */
    public static String getMovieCategories(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String movieCategories = sharedPreferences.getString(context.getString(R.string.movies_categories_key), context.getString(R.string.default_movies_categories));
        return movieCategories;
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


}
