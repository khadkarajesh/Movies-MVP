package com.example.rajesh.popularmovies.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajesh.popularmovies.R;
import com.example.rajesh.popularmovies.rest.model.Movie;
import com.example.rajesh.popularmovies.rest.model.MovieComment;
import com.example.rajesh.popularmovies.rest.model.MovieTrailer;
import com.example.rajesh.popularmovies.utils.Utility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    public static final String MOVIES_OBJECT = "movie";
    public static final String MOVIE_OBJECT_BUNDLE = "movie_bundle";
    public static final String YOUTUBE_INTENT_BASE_URI = "vnd.youtube://";
    private Movie movie;

    @Bind(R.id.img_movie_poster)
    ImageView moviePoster;

    @Bind(R.id.tv_movie_title)
    TextView movieTitle;

    @Bind(R.id.tv_releasing_date)
    TextView releasingDate;

    @Bind(R.id.tv_overview)
    TextView overView;

    @Bind(R.id.rb_movie_rating)
    RatingBar ratingBar;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Bind(R.id.ll_comments)
    LinearLayout llComments;

    @Nullable
    @Bind(R.id.ll_trailers)
    LinearLayout llTrailers;

    @Bind(R.id.tv_comment_title)
    TextView tvCommentTitle;

    @Nullable
    @Bind(R.id.tv_trailer_title)
    TextView tvTrailerTitle;

    private MovieDetailPresenter movieDetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        movie = getIntent().getExtras().getBundle(MOVIE_OBJECT_BUNDLE).getParcelable(MOVIES_OBJECT);

        movieDetailPresenter = new MovieDetailPresenter(this);
        movieDetailPresenter.getMovieComments(movie.id);
        movieDetailPresenter.getMovieTrailers(movie.id);

        setToolbar();
        setData();
    }

    private void setData() {
        Picasso.with(this).load(Utility.getImageUri(movie.posterPath))
                .into(moviePoster);
        movieTitle.setText(movie.title);
        ratingBar.setRating(movie.voteAverage);
        overView.setText(movie.overview);
        releasingDate.setText(movie.releaseDate);
    }

    private void setToolbar() {
        //set the toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout.setTitle(movie.title);
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
    }

    @Override
    public void showComments(ArrayList<MovieComment> movieComments) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        for (MovieComment comment : movieComments) {

            View view = layoutInflater.inflate(R.layout.layout_movie_comments, llComments, false);
            TextView tvCommenterName = ButterKnife.findById(view, R.id.tv_commenter_name);
            TextView tvComment = ButterKnife.findById(view, R.id.tv_comment);

            tvComment.setText(comment.content);
            tvCommenterName.setText(comment.author);

            llComments.addView(view);
        }
    }

    @Override
    public void showMovieTrailers(ArrayList<MovieTrailer> movieTrailers) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < movieTrailers.size(); i++) {

            View view = layoutInflater.inflate(R.layout.layout_movie_trailers, llComments, false);
            LinearLayout llTrailerWrapper = ButterKnife.findById(view, R.id.ll_trailer_wrapper);


            //add image view containing play icon for movie trailers.
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60, 60);
            layoutParams.rightMargin = 22;
            ImageView ivPlayIcon = new ImageView(this);
            ivPlayIcon.setTag(movieTrailers.get(i));
            ivPlayIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.btn_play));
            ivPlayIcon.setLayoutParams(layoutParams);

            ivPlayIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieTrailer movieTrailer = (MovieTrailer) v.getTag();
                    playTrailer(movieTrailer.key);
                }
            });


            //text view for showing the trailer name.
            LinearLayout.LayoutParams paramsTvTrailer = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            TextView tvTrailer = new TextView(this);

            tvTrailer.setText("trailer ");
            tvTrailer.setGravity(Gravity.CENTER_VERTICAL);


            llTrailerWrapper.addView(ivPlayIcon, layoutParams);
            llTrailerWrapper.addView(tvTrailer, paramsTvTrailer);

            llTrailers.addView(llTrailerWrapper);
        }
    }

    @Override
    public void showCommentTextView() {
        tvCommentTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCommentTextView() {
        tvCommentTitle.setVisibility(View.GONE);
    }

    @Override
    public void showTrailerTextView() {
        tvTrailerTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTrailerTextView() {
        tvTrailerTitle.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(MovieDetailActivity.this, "error message " + message, Toast.LENGTH_SHORT).show();
    }

    /**
     * opens the youtube application via intent
     *
     * @param key
     */
    private void playTrailer(String key) {
        if (key != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_INTENT_BASE_URI + key));
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        movieDetailPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieDetailPresenter.onResume();
    }

    public static Intent getLaunchIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(MOVIES_OBJECT, movie);
        intent.putExtra(MOVIE_OBJECT_BUNDLE, bundle);
        return intent;
    }
}
