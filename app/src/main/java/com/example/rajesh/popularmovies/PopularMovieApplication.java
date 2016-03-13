package com.example.rajesh.popularmovies;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by rajesh on 10/29/15.
 */
public class PopularMovieApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }
}
