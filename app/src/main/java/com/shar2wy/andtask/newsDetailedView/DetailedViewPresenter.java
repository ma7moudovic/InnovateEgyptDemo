package com.shar2wy.andtask.newsDetailedView;

import android.content.Context;
import android.util.Log;

import com.shar2wy.andtask.api.ApiClient;
import com.shar2wy.andtask.api.ApiInterface;
import com.shar2wy.andtask.models.News;
import com.shar2wy.andtask.models.NewsDetailedResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shar2wy on 5/10/17.
 */

public class DetailedViewPresenter {

    private final Context context;
    private final NewsDetailsPresenterListener mListener;

    public interface NewsDetailsPresenterListener {
        void onGetNewsDetailsSuccess(News news);

        void onGetNewsDetailsFail(String error);
    }

    public DetailedViewPresenter(NewsDetailsPresenterListener listener, Context context) {
        this.mListener = listener;
        this.context = context;
    }

    public void getNewsDetails(String nid) {
        ApiClient
                .getClient()
                .create(ApiInterface.class)
                .getNewsDetails(nid)
                .enqueue(new Callback<NewsDetailedResponse>() {
                    @Override
                    public void onResponse(Call<NewsDetailedResponse> call, Response<NewsDetailedResponse> response) {

                        NewsDetailedResponse result = response.body();
                        Log.d("response", result.getNews().toString());
                        if (result != null)
                            mListener.onGetNewsDetailsSuccess(result.getNews());
                    }

                    @Override
                    public void onFailure(Call<NewsDetailedResponse> call, Throwable t) {
                        mListener.onGetNewsDetailsFail(t.getMessage());
                    }
                });
    }
}
