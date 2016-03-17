package com.example.rajesh.popularmovies.dagger.module;

import com.example.rajesh.popularmovies.PopularMovieApplication;
import com.example.rajesh.popularmovies.rest.service.IMovieService;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


@Module
public class NetworkModule {
    private final String END_POINT = "http://api.themoviedb.org/";
    private PopularMovieApplication popularMovieApplication;

    public NetworkModule(PopularMovieApplication popularMovieApplication) {
        this.popularMovieApplication = popularMovieApplication;
    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    public GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public Retrofit getRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(END_POINT)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    public IMovieService getIMovieService(Retrofit retrofit) {
        return retrofit.create(IMovieService.class);
    }
}
