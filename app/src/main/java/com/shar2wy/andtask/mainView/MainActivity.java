package com.shar2wy.andtask.mainView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shar2wy.andtask.R;
import com.shar2wy.andtask.mainView.adapters.NavigationItemsAdapter;
import com.shar2wy.andtask.mainView.adapters.NewsAdapter;
import com.shar2wy.andtask.models.NavItem;
import com.shar2wy.andtask.models.News;
import com.shar2wy.andtask.newsDetailedView.NewsDetailedActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NewsAdapter.OnNewsClickListener,
        MainPresenter.NewsPresenterListener,
        NavigationItemsAdapter.OnNavItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    MainPresenter mainPresenter;

    RecyclerView recyclerViewNews;
    NewsAdapter mNewsAdapter;
    List<News> mNews = new ArrayList<>();
    LinearLayoutManager layoutManager;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_activity_news));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        RecyclerView recyclerView = (RecyclerView) navigationView.getHeaderView(0); // 0-index header

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<NavItem> navItems = new ArrayList<>();

        NavigationItemsAdapter navigationItemsAdapter = new NavigationItemsAdapter(this, navItems);
        navigationItemsAdapter.setmOnNavItemClickListener(this);
        RecyclerView.LayoutManager layoutManagerNav = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerNav);
        recyclerView.setAdapter(navigationItemsAdapter);
        navigationView.setNavigationItemSelectedListener(this);

        navItems.add(new NavItem("News", R.drawable.news_icon));
        navItems.add(new NavItem("Innovation Map", R.drawable.map_icon));
        navItems.add(new NavItem("Events Calendar", R.drawable.events_icon));
        navItems.add(new NavItem("Leadership Thoughts", R.drawable.leadership_icon));
        navItems.add(new NavItem("Language", R.drawable.language));

        navigationItemsAdapter.notifyDataSetChanged();
//        navigationView.setNavigationItemSelectedListener(this);


        mainPresenter = new MainPresenter(this, this);
        mainPresenter.getNews();

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerViewNews = (RecyclerView) findViewById(R.id.recyclerView_news);
        recyclerViewNews.setHasFixedSize(true);
        recyclerViewNews.setItemAnimator(new DefaultItemAnimator());
//
        mNewsAdapter = new NewsAdapter(this, mNews);
        mNewsAdapter.setmOnNewsClickListener(this);
        layoutManager = new LinearLayoutManager(this);
//
        recyclerViewNews.setLayoutManager(layoutManager);
        recyclerViewNews.setAdapter(mNewsAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void OnNewsClick(News news) {

        Intent intent = new Intent(this, NewsDetailedActivity.class)
                .putExtra(NewsDetailedActivity.DETAILED_NEWS, news);
        startActivity(intent);
    }

    @Override
    public void onGetNewsSuccess(List<News> newsList) {
        progressBar.setVisibility(View.GONE);
        mNews.addAll(newsList);
        mNewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetNewsFail(String error) {
        progressBar.setVisibility(View.GONE);
        showRetryLayout();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    private void showRetryLayout() {

    }

    @Override
    public void OnNavItemClick(NavItem navItem) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
