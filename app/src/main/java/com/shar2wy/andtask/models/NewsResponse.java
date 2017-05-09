package com.shar2wy.andtask.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shar2wy on 5/10/17.
 */

public class NewsResponse {

    @SerializedName("News")
    private List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
