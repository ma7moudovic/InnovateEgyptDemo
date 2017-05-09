package com.shar2wy.andtask.mainView.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shar2wy.andtask.R;
import com.shar2wy.andtask.models.News;

import java.util.List;

/**
 * Created by shar2wy on 5/10/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context mContext;
    List<News> newsList;
    OnNewsClickListener mOnNewsClickListener;

    public NewsAdapter(Context mContext, List<News> newsList) {
        this.mContext = mContext;
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        final News news = newsList.get(position);

        holder.newsTitle.setText(news.getNewsTitle());
        holder.newsDate.setText(news.getPostDate());
        holder.newsLikes.setText(mContext.getString(R.string.likes,news.getLikes()));
        holder.newsViews.setText(mContext.getString(R.string.views,news.getNumOfViews()));

        Glide
                .with(mContext)
                .load(news.getImageUrl())
                .crossFade()
                .into(holder.newsImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnNewsClickListener != null) {
                    mOnNewsClickListener.OnNewsClick(news);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setmOnNewsClickListener(OnNewsClickListener listener) {
        mOnNewsClickListener = listener;
    }

    public interface OnNewsClickListener {

        void OnNewsClick(News news);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        TextView newsDate;
        TextView newsLikes;
        TextView newsViews;
        ImageView newsImage;

        public NewsViewHolder(View v) {
            super(v);
            newsTitle = (TextView) v.findViewById(R.id.news_title);
            newsDate = (TextView) v.findViewById(R.id.news_date);
            newsLikes = (TextView) v.findViewById(R.id.news_likes);
            newsViews = (TextView) v.findViewById(R.id.news_views);
            newsImage = (ImageView) v.findViewById(R.id.news_image);
        }
    }
}
