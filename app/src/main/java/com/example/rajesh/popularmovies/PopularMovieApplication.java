package com.example.rajesh.popularmovies;

import android.app.Application;

import com.example.rajesh.popularmovies.dagger.module.AppModule;
import com.example.rajesh.popularmovies.dagger.module.component.DaggerMovieComponent;
import com.example.rajesh.popularmovies.dagger.component.MovieComponent;

import timber.log.Timber;

/**
 * Created by rajesh on 10/29/15.
 */
public class PopularMovieApplication extends Application {
    public static MovieComponent movieComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        movieComponent = DaggerMovieComponent.builder().appModule(new AppModule(this)).build();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static MovieComponent getMovieComponent() {
        return movieComponent;
    }


}
