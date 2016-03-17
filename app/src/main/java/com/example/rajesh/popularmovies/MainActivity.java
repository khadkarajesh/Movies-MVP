package com.example.rajesh.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rajesh.popularmovies.Movies.MoviesActivity;
import com.example.rajesh.popularmovies.rest.model.MoviesInfo;
import com.example.rajesh.popularmovies.rest.service.IMovieService;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    IMovieService movieService;
    Button saveButton, showButton;
    EditText editText;
    public static final String NAME = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, MoviesActivity.class));
        finish();

        PopularMovieApplication.getMovieComponent().inject(this);

        bindView();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString(NAME, editText.getText().toString()).commit();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "value :: " + sharedPreferences.getString(NAME, "rajesh khadka"), Toast.LENGTH_SHORT).show();
            }
        });


        Call<MoviesInfo> call = movieService.getMoviesInfo("popular", 1, BuildConfig.MOVIE_API_KEY);
        call.enqueue(new Callback<MoviesInfo>() {
            @Override
            public void onResponse(Response<MoviesInfo> response, Retrofit retrofit) {
                for (int i = 0; i < response.body().movieList.size(); i++) {
                    Log.d(TAG, "onResponse: " + response.body().movieList.get(i).title);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void bindView() {
        saveButton = (Button) findViewById(R.id.save_data);
        showButton = (Button) findViewById(R.id.show_data);
        editText = (EditText) findViewById(R.id.edit_text);
    }
}
