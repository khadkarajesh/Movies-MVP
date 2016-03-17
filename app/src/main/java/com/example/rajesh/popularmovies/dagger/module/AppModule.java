package com.example.rajesh.popularmovies.dagger.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.rajesh.popularmovies.PopularMovieApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    PopularMovieApplication popularMovieApplication;

    public AppModule(PopularMovieApplication movieApplication) {
        this.popularMovieApplication = movieApplication;
    }

    @Provides
    @Singleton
    PopularMovieApplication getApplicationContext() {
        return new PopularMovieApplication();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(popularMovieApplication);
    }

}
