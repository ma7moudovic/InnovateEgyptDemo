package com.shar2wy.andtask.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shar2wy on 5/10/17.
 */

public class NewsDetailedResponse {

    @SerializedName("newsItem")
    News news;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
