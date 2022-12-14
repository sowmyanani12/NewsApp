package com.project.newsapp.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.newsapp.R;
import com.project.newsapp.adapters.AdapterListNews;
import com.project.newsapp.clicklisteners.AdapterItemClickListener;
import com.project.newsapp.clicklisteners.NewsDialogClickListeners;
import com.project.newsapp.databinding.NewsDialogBinding;
import com.project.newsapp.db.DatabaseEngine;
import com.project.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity implements AdapterItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Context context;
    AdapterListNews adapterListNews;
    List<News> newsList = new ArrayList<>();

    Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        context = this;
        ButterKnife.bind(this);

        adapterListNews = new AdapterListNews(newsList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapterListNews);

        executor.execute(() -> {
            newsList = DatabaseEngine.getInstance().getDBInstance().memoriesDao().getAllNews();
            adapterListNews.updateNewsList(newsList);
        });

    }

    @Override
    public void onNewsItemClick(News news) {
        showDialogPolygon(news);
    }

    private void showDialogPolygon(News news) {
        final Dialog dialog = new Dialog(this);
        NewsDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getApplicationContext()), R.layout.dialog_header_polygon, null, false);
        binding.setNews(news);
        binding.setListener(new NewsDialogClickListeners() {
            @Override
            public void onGotoWebSiteClick(String url) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onDismissClick() {
                dialog.dismiss();
            }
        });

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        dialog.show();
    }

}