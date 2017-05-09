package com.shar2wy.andtask.mainView;

import android.content.Context;
import android.util.Log;

import com.shar2wy.andtask.api.ApiClient;
import com.shar2wy.andtask.api.ApiInterface;
import com.shar2wy.andtask.models.News;
import com.shar2wy.andtask.models.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shar2wy on 5/10/17.
 */

public class MainPresenter {

    private final Context context;
    private final NewsPresenterListener mListener;

    public interface NewsPresenterListener {
        void onGetNewsSuccess(List<News> newsList);

        void onGetNewsFail(String error);
    }

    public MainPresenter(NewsPresenterListener listener, Context context) {
        this.mListener = listener;
        this.context = context;
    }

    public void getNews() {
        ApiClient
                .getClient()
                .create(ApiInterface.class).getNews()
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        NewsResponse result = response.body();
                        Log.d("response",result.getNewsList().size()+"");
                        if(result != null)
                            mListener.onGetNewsSuccess(result.getNewsList());
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        mListener.onGetNewsFail(t.getMessage());
                    }
                });
    }
}
