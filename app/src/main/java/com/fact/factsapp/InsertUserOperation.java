package com.fact.factsapp;

import android.os.AsyncTask;

public class InsertUserOperation extends AsyncTask<User, Void, String> {
    UserOperations listener;

    public InsertUserOperation(UserOperations listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(User... users) {
        try {
            MyApp.getmAppDatabase().userDao().insertAll(users);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String result) {
        listener.insertUsers(result);
    }
}
