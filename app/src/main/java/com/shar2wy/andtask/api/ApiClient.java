package com.shar2wy.andtask.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shar2wy on 5/10/17.
 */

public class ApiClient {
    public static final String BASE_URL = "http://egyptinnovate.com/en/api/v01/safe/";
    private static Retrofit retrofit = null;


    public static synchronized Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
