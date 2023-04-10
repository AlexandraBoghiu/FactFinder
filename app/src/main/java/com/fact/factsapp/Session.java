package com.fact.factsapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private final String SESSION_KEY = "session";
    private static Session instance = null;

    private Session(Context context) {
        sharedPreferences = context.getSharedPreferences("register prefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static Session getInstance(Context context) {
        if (instance == null) {
            instance = new Session(context);
        }
        return instance;
    }

    public void setLoggedIn(boolean loggedIn) {
        editor.putBoolean(SESSION_KEY, loggedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(SESSION_KEY, false);
    }
}