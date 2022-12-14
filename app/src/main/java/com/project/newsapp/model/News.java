package com.project.newsapp.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "News")
public class News {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
//    @Embedded
    @SerializedName("source")
    private Source source;

    @SerializedName("title")
    private String newsTitle;

    @SerializedName("description")
    private String newsDescription;

    @SerializedName("url")
    private String newsUrl;

    @SerializedName("urlToImage")
    private String newsImage;

    @Ignore
//    @Embedded
    @SerializedName("publishedAt")
    private Date newsPublishedDate;

    @NonNull
    public Source getSource() {
        return source;
    }

    public void setSource(@NonNull Source source) {
        this.source = source;
    }

    @NonNull
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(@NonNull String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @NonNull
    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(@NonNull String newsDescription) {
        this.newsDescription = newsDescription;
    }

    @NonNull
    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(@NonNull String newsUrl) {
        this.newsUrl = newsUrl;
    }

    @NonNull
    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(@NonNull String newsImage) {
        this.newsImage = newsImage;
    }

    @NonNull
    public String getNewsPublishedDate() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(newsPublishedDate);
    }

    public void setNewsPublishedDate(@NonNull Date newsPublishedDate) {
        this.newsPublishedDate = newsPublishedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Added for Child JSON Object
    @Entity
    public class Source {
        @SerializedName("name")
        private String sourceName;

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }
    }

    //Image Binding - I didn't write newsviewmodel for just this method
    @BindingAdapter({"bind:imgUrl"})
    public static void setImage(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }
}
