package com.vipshah.retrofitdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final List<MagicMovie> magicMovies;

    public MoviesAdapter(List<MagicMovie> magicMovies) {
        this.magicMovies = magicMovies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MagicMovie magicMovie = magicMovies.get(position);

        Picasso
                .with(holder.imageView.getContext())
                .load("https://image.tmdb.org/t/p/w780" + magicMovie.getPoster())
                .fit()
                .into(holder.imageView);

        holder.movieTitle.setText(magicMovie.getTitle());
    }

    @Override
    public int getItemCount() {
        return magicMovies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        final TextView movieTitle;

        MovieViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
        }
    }
}
