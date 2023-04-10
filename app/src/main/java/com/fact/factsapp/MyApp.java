package com.fact.factsapp;

import android.app.Application;

import androidx.room.Room;

public class MyApp extends Application {
    private static MyApp mInstance;
    private static AppDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mAppDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mydb").build();
    }

    public static MyApp getmInstance() {
        return mInstance;
    }

    public static AppDatabase getmAppDatabase() {
        return mAppDatabase;
    }
}
