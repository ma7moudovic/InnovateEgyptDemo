package com.shar2wy.andtask.api;

import com.shar2wy.andtask.models.NewsDetailedResponse;
import com.shar2wy.andtask.models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shar2wy on 5/10/17.
 */

public interface ApiInterface {

    @GET("GetNews")
    Call<NewsResponse> getNews();

    @GET("GetNewsDetails")
    Call<NewsDetailedResponse> getNewsDetails(@Query("nid") String nid);

}
