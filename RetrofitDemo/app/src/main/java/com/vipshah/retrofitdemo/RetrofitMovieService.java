package com.vipshah.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitMovieService {

    @GET("now_playing")
    Call<MagicMovies> getMovies(@Query("api_key") String apiKey);
}
