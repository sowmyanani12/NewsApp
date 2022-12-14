package com.project.newsapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.project.newsapp.model.News;

@Database(entities = {News.class}, version = 2)
public abstract class DatabaseClass extends RoomDatabase {
    public abstract NewsDao memoriesDao();
}
