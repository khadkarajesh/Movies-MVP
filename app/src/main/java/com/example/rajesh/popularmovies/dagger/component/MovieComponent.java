package com.example.rajesh.popularmovies.dagger.component;

import com.example.rajesh.popularmovies.MainActivity;
import com.example.rajesh.popularmovies.Movies.MoviesActivity;
import com.example.rajesh.popularmovies.Movies.MoviesModel;
import com.example.rajesh.popularmovies.dagger.module.AppModule;
import com.example.rajesh.popularmovies.dagger.module.NetworkModule;
import com.example.rajesh.popularmovies.moviedetail.MovieDetailModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rajesh on 3/16/16.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface MovieComponent {
    void inject(MoviesActivity moviesActivity);

    void inject(MainActivity mainActivity);

    void inject(MoviesModel moviesModel);

    void inject(MovieDetailModel movieDetailModel);
}
