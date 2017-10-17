package com.vipshah.retrofitdemo;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivity {

    private RecyclerView moviesRecylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRecylerView = findViewById(R.id.moviesRecyclerView);

        Retrofit retrofit = RetrofitMovieClient
                .getClient("https://api.themoviedb.org/3/movie/");
        RetrofitMovieService service = retrofit.create(RetrofitMovieService.class);

        service.getMovies("55957fcf3ba81b137f8fc01ac5a31fb5")
                .enqueue(new Callback<MagicMovies>() {
                    @Override
                    public void onResponse(Call<MagicMovies> call, Response<MagicMovies> response) {
                        if (response.isSuccessful()) {
                            MagicMovies magicMovies = response.body();
                            if (magicMovies != null) {
                                displayAllMovies(magicMovies.getMovies());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MagicMovies> call, Throwable t) {
                        handleError(t);
                    }
                });
    }

    private void displayAllMovies(List<MagicMovie> movies) {
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies);
        moviesRecylerView.setLayoutManager(new GridLayoutManager(this, 2));
        moviesRecylerView.setAdapter(moviesAdapter);
    }
}
