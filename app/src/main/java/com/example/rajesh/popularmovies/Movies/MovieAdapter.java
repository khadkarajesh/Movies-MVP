package com.example.rajesh.popularmovies.Movies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rajesh.popularmovies.R;
import com.example.rajesh.popularmovies.rest.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieAdapter extends BaseAdapter {

    ArrayList<Movie> movies = new ArrayList<>();
    LayoutInflater inflater;
    private final String IMAGE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w342";
    private Context mContext;


    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.mContext = context;
        movies.addAll(movies);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Movie movie = movies.get(position);
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.single_movie_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.tvMovieTitle.setText(movie.title);
        Picasso.with(mContext).load(getImageUri(movie.posterPath)).placeholder(R.drawable.abc).into(holder.imgPoster);

        return convertView;
    }


    public void addMoreMovies(ArrayList<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public boolean isAdapterEmpty() {
        return movies.isEmpty();
    }

    public Movie getMovieAtPosition(int position) {
        return movies.get(position);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    static class ViewHolder {
        @Bind(R.id.tv_movie_title)
        TextView tvMovieTitle;

        @Bind(R.id.img_movie_poster)
        ImageView imgPoster;

        @Bind(R.id.card_view)
        CardView cardView;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public String getImageUri(String uri) {
        return IMAGE_POSTER_BASE_URL + "/" + uri;
    }
}
