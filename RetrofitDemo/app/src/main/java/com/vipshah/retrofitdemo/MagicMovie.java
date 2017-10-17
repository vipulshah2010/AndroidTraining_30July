package com.vipshah.retrofitdemo;

import com.google.gson.annotations.SerializedName;

public class MagicMovie {

    @SerializedName("vote_average")
    private double votes;
    private String title;
    @SerializedName("poster_path")
    private String poster;
    private String overview;

    public double getVotes() {
        return votes;
    }

    public void setVotes(double votes) {
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
