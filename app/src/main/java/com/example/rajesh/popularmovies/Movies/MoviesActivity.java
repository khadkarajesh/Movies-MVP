package com.example.rajesh.popularmovies.Movies;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rajesh.popularmovies.R;
import com.example.rajesh.popularmovies.moviedetail.MovieDetailActivity;
import com.example.rajesh.popularmovies.rest.model.Movie;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MoviesActivity extends AppCompatActivity implements MoviesView {
    @Bind(R.id.gridView)
    GridView gridView;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    MovieAdapter movieAdapter;
    MoviesPresenter moviesPresenter;

    int page = 1;

    private static final String MOVIES = "movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        moviesPresenter = new MoviesPresenter(this);
        setMoviesAdapter();

        if (savedInstanceState != null) {
            ArrayList<Movie> movies = savedInstanceState.getParcelableArrayList(MOVIES);
            moviesPresenter.onSuccess(movies);
        } else {
            moviesPresenter.getMovies(page);
        }


        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Timber.d("first visible item  %d :: visible item count %d ", firstVisibleItem, visibleItemCount);
                if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                    moviesPresenter.loadMore(page++);
                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(MovieDetailActivity.MOVIES_OBJECT, movieAdapter.getMovieAtPosition(position));
                intent.putExtra(MovieDetailActivity.MOVIE_OBJECT_BUNDLE, bundle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MoviesActivity.this, view, getResources().getString(R.string.shared_transition));
                    startActivity(intent, optionsCompat.toBundle());
                } else {
                    startActivity(intent);
                }

            }
        });
    }

    private void setMoviesAdapter() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        movieAdapter = new MovieAdapter(MoviesActivity.this, arrayList);
        gridView.setAdapter(movieAdapter);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        movieAdapter.addMoreMovies(movies);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(MoviesActivity.this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkIfMoviesEmpty() {
        return movieAdapter.isAdapterEmpty();
    }

    @Override
    public void addMoreMovies(ArrayList<Movie> movies) {
        movieAdapter.addMoreMovies(movies);
    }

    @Override
    protected void onResume() {
        super.onResume();
        moviesPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        moviesPresenter.onPause();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MOVIES, movieAdapter.getMovies());
    }
}
