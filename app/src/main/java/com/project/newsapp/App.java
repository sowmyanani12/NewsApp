package com.project.newsapp;

import android.app.Application;

import com.project.newsapp.db.DatabaseEngine;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseEngine.getInstance(this).initializeDatabase();

    }
}
