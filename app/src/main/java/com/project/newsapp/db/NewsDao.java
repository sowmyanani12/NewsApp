package com.project.newsapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.newsapp.model.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("select * from News")
    List<News> getAllNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNewNews(News news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<News> news);

    @Delete
    void deleteNews(News news);

    @Query("delete from News")
    void deleteAllNews();
}
