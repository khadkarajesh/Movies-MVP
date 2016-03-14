package com.example.rajesh.popularmovies.Movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rajesh.popularmovies.R;
import com.example.rajesh.popularmovies.moviedetail.MovieDetailViewActivity;
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

    MovieAdapter movieAdapter;
    MoviesPresenterContract moviesPresenterContract;

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        moviesPresenterContract = new MoviesPresenter(this);
        moviesPresenterContract.getMovies(page);

        ArrayList<Movie> arrayList = new ArrayList<>();

        movieAdapter = new MovieAdapter(MoviesActivity.this, arrayList);
        gridView.setAdapter(movieAdapter);

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Timber.d("first visible item  %d :: visible item count %d ", firstVisibleItem, visibleItemCount);
                if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                    moviesPresenterContract.loadMore(page++);
                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(MovieDetailViewActivity.getLaunchIntent(MoviesActivity.this, movieAdapter.getMovieAtPosition(position)));
            }
        });
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
        moviesPresenterContract.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        moviesPresenterContract.onPause();
    }
}
