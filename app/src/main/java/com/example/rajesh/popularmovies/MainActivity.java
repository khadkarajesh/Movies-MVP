package com.example.rajesh.popularmovies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;
    Button saveButton, showButton;
    EditText editText;
    public static final String NAME = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    private void bindView() {
        saveButton = (Button) findViewById(R.id.save_data);
        showButton = (Button) findViewById(R.id.show_data);
        editText = (EditText) findViewById(R.id.edit_text);
    }
}
