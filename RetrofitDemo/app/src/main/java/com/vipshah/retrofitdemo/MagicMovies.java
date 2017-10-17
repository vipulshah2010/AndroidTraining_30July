package com.vipshah.retrofitdemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MagicMovies {
    @SerializedName("results")
    private List<MagicMovie> movies;

    public List<MagicMovie> getMovies() {
        return movies;
    }
}
