package com.shar2wy.andtask.newsDetailedView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shar2wy.andtask.R;
import com.shar2wy.andtask.models.News;

public class NewsDetailedActivity extends AppCompatActivity implements DetailedViewPresenter.NewsDetailsPresenterListener {

    public static String DETAILED_NEWS = "detailed_news";

    private News mNewsModel;
    private DetailedViewPresenter mDetailedViewPresenter;

    TextView detailedNewsLikes;
    TextView detailedNewsViews;
    TextView detailedNewsTitle;
    TextView detailedNewsDesc;
    ImageView detailedNewsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detailed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (getIntent() != null && getIntent().getExtras() != null) {
            mNewsModel = (News) getIntent().getExtras().getSerializable(DETAILED_NEWS);
        }

        mDetailedViewPresenter = new DetailedViewPresenter(this, this);
        mDetailedViewPresenter.getNewsDetails(mNewsModel.getNid());

        initView();

        if (mNewsModel != null) {
            updateView(mNewsModel);
            updateNewsImage(mNewsModel.getImageUrl());
        }
    }

    private void updateView(News news) {

        detailedNewsTitle.setText(news.getNewsTitle());
        detailedNewsLikes.setText(getString(R.string.likes, news.getLikes()));
        detailedNewsViews.setText(getString(R.string.views, news.getNumOfViews()));

        if (news.getDescription() != null) {
            detailedNewsDesc.setText(news.getDescription());
        }
    }

    private void updateNewsImage(String url) {

        if (url != null || url != "") {
            Glide
                    .with(this)
                    .load(url)
                    .crossFade()
                    .into(detailedNewsImage);

        }

    }

    private void initView() {

        detailedNewsTitle = (TextView) findViewById(R.id.detailed_news_title);
        detailedNewsLikes = (TextView) findViewById(R.id.detailed_news_likes);
        detailedNewsViews = (TextView) findViewById(R.id.detailed_news_views);
        detailedNewsDesc = (TextView) findViewById(R.id.detailed_news_desc);
        detailedNewsImage = (ImageView) findViewById(R.id.detailed_news_image);

    }

    @Override
    public void onGetNewsDetailsSuccess(News news) {
        updateView(news);
    }

    @Override
    public void onGetNewsDetailsFail(String error) {
        Toast.makeText(this, getString(R.string.get_news_details_fail) + ", " + error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailed_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
